package com.gaurav.bugtrackingsystem.controllers;

import com.gaurav.bugtrackingsystem.dtos.ErrorResponse;
import com.gaurav.bugtrackingsystem.dtos.project_dtos.CreateProjectRequestDto;
import com.gaurav.bugtrackingsystem.dtos.project_dtos.CreateProjectResponseDto;
import com.gaurav.bugtrackingsystem.dtos.user_dtos.GetAllUsersResponseDto;
import com.gaurav.bugtrackingsystem.models.Project;
import com.gaurav.bugtrackingsystem.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequestDto requestDto) {
        ResponseEntity<CreateProjectResponseDto> response;
        try {
            // read request dto parameters
            String name = requestDto.getName();
            String description = requestDto.getDescription();
            Date startDate = requestDto.getStartDate();
            Date endDate = requestDto.getClosedDate();
            // call api
            Project project =  projectService.createProject(name,  description, startDate, endDate);
            // fill response dto
            CreateProjectResponseDto responseDto = new CreateProjectResponseDto();
            responseDto.setId(project.getId());
            responseDto.setName(project.getName());
            responseDto.setDescription(project.getDescription());
            responseDto.setStartDate(project.getStartDate());
            responseDto.setClosedDate(project.getClosedDate());
            responseDto.setProjectStatus(project.getProjectStatus());
            // create response
            response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
