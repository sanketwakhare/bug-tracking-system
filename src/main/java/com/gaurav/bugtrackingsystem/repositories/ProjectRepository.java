package com.gaurav.bugtrackingsystem.repositories;

import com.gaurav.bugtrackingsystem.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
