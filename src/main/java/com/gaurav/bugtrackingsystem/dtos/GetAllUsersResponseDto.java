package com.gaurav.bugtrackingsystem.dtos;

import com.gaurav.bugtrackingsystem.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllUsersResponseDto {
    private List<User> users;
}
