package com.gaurav.bugtrackingsystem.dtos.project_dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateProjectRequestDto {
    private String name;
    private String description;
    private Date startDate;
    private Date closedDate;
}
