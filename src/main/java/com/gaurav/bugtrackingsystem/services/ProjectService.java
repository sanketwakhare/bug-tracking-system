package com.gaurav.bugtrackingsystem.services;

import com.gaurav.bugtrackingsystem.models.Project;
import com.gaurav.bugtrackingsystem.models.ProjectStatus;
import com.gaurav.bugtrackingsystem.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(String name, String description, Date startDate, Date endDate) {
        // validations
        if(startDate.getTime() >= endDate.getTime()) {
            // invalid date range
        } else if(Objects.isNull(name)) {
            // missing field, name required
        }

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setClosedDate(endDate);
        // default project status
        project.setProjectStatus(ProjectStatus.NOT_STARTED);
        Date now = new Date();
        project.setCreatedAt(now);
        project.setLastModifiedAt(now);
        return projectRepository.saveAndFlush(project);
    }
}
