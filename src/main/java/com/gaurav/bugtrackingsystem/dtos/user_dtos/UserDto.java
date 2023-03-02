package com.gaurav.bugtrackingsystem.dtos.user_dtos;

import com.gaurav.bugtrackingsystem.models.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public UserDto(Long id, String name, RoleType roleType) {
        this.id = id;
        this.name = name;
        this.roleType = roleType;
    }
}
