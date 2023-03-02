package com.gaurav.bugtrackingsystem.dtos;

import com.gaurav.bugtrackingsystem.models.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserSignUpRequestDto {
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType role;
}
