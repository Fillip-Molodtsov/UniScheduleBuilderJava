package com.springproj.schedulebuilder.model.domain.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("USERS")
public class AppUser {
    @Id
    private final Integer id;
    private String username;
    private String password;
}
