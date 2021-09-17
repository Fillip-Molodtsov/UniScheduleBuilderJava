package com.springproj.schedulebuilder.security.jwt;

import com.springproj.schedulebuilder.config.CustomJwtConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtUtil implements IJwtService {

    private final CustomJwtConfigProperties customConfigProperties;

    public String generateToken(Collection<GrantedAuthority> authorities, String subject) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", authorities);
        return createToken(claims, subject);
    }

    public UsernamePasswordAuthenticationToken getUserPassAuthToken(String token) {
        return extractClaim(token, claims -> {
            String sub = claims.getSubject();
            var authorities = (List<Map<String, String>>) claims.get("authorities");
            var set = authorities.stream()
                    .map(a -> new SimpleGrantedAuthority(a.get("authority")))
                    .collect(Collectors.toSet());
            return new UsernamePasswordAuthenticationToken(sub, null, set);
        });
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(customConfigProperties.getSecret().getBytes())).parseClaimsJws(token).getBody();
    }

    public String generateToken(Authentication authResults) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", authResults.getAuthorities());
        return createToken(claims, authResults.getName());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, customConfigProperties.getHoursValid());

        return Jwts.builder()
                .setClaims(claims).setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(calendar.getTime())
                .signWith(Keys.hmacShaKeyFor(customConfigProperties.getSecret().getBytes()))
                .compact();
    }
}
