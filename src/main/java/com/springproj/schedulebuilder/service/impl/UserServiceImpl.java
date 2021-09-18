package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.model.domain.user.AppUser;
import com.springproj.schedulebuilder.model.domain.user.CustomUserDetails;
import com.springproj.schedulebuilder.repository.AppUserRepository;
import com.springproj.schedulebuilder.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Transactional
    @Override
    public void signUp(AppUser user) {
        var existingUser = appUserRepository.findByUsername(
                user.getUsername());
        if(existingUser.isPresent()) throw new IllegalStateException("That username is already taken");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
    }
}
