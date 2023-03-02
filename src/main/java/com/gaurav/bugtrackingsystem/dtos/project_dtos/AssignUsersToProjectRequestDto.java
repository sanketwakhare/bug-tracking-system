package com.gaurav.bugtrackingsystem.dtos.project_dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignUsersToProjectRequestDto {
    private Long projectId;
    private List<Long> userIds;
}
