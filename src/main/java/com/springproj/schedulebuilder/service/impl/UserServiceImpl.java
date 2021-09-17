package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.model.domain.user.CustomUserDetails;
import com.springproj.schedulebuilder.repository.AppUserRepository;
import com.springproj.schedulebuilder.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var appUser = appUserRepository
                .findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Not found user with username: " + s));
        var user = new CustomUserDetails();
        BeanUtils.copyProperties(appUser, user);
        user.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_USER")));
        return user;
    }
}
