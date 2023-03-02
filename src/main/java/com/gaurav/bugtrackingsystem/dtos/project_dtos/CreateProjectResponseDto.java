package com.gaurav.bugtrackingsystem.dtos.project_dtos;

import com.gaurav.bugtrackingsystem.models.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
@Setter
public class CreateProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date closedDate;
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
}
