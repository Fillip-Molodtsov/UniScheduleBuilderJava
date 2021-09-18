package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.model.domain.user.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    void signUp(AppUser user);
}
