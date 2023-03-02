package com.gaurav.bugtrackingsystem.services;

import com.gaurav.bugtrackingsystem.models.Project;
import com.gaurav.bugtrackingsystem.models.ProjectStatus;
import com.gaurav.bugtrackingsystem.models.User;
import com.gaurav.bugtrackingsystem.repositories.ProjectRepository;
import com.gaurav.bugtrackingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
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

    public List<User> assignUsers(Long projectId, List<Long> userIds) {
        // validations
        // get project
        Optional<Project> dbProject = projectRepository.findById(projectId);
        if(dbProject.isEmpty()) {
            throw new InvalidParameterException("projectId "+ projectId);
        }
        // get all users within userIds
        List<User> dbUsers = userRepository.findAllById(userIds);
        if(dbUsers.isEmpty()) {
            // users not found
            throw new InvalidParameterException("userIds "+ userIds);
        }
        // assign to project
        Project project = dbProject.get();
        project.setUsers(dbUsers);
        Date now = new Date();
        project.setLastModifiedAt(now);
        // save project
        projectRepository.saveAndFlush(project);
        return dbUsers;
    }
}
