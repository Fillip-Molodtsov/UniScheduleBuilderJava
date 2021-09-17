package com.springproj.schedulebuilder.repository;

import com.springproj.schedulebuilder.model.domain.user.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}
