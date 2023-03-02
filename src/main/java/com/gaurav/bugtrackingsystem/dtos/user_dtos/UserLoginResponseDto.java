package com.gaurav.bugtrackingsystem.dtos.user_dtos;

import com.gaurav.bugtrackingsystem.models.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserLoginResponseDto {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private RoleType role;
}