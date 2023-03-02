package com.gaurav.bugtrackingsystem.dtos.project_dtos;

import com.gaurav.bugtrackingsystem.dtos.user_dtos.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignUsersToProjectResponseDto {
    private Long projectId;
    private List<UserDto> users;
}
