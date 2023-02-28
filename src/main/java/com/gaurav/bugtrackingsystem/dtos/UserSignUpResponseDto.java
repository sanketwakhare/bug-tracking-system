package com.gaurav.bugtrackingsystem.dtos;

import com.gaurav.bugtrackingsystem.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpResponseDto {
    private Long id;
    private String name;
}
