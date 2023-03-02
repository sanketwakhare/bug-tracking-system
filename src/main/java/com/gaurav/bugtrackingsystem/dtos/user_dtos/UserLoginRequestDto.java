package com.gaurav.bugtrackingsystem.dtos.user_dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
    private String name;
    private String password;
}
