package com.gaurav.bugtrackingsystem.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "projects")
public class Project extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    private String name;

    private String description;

    private Date startDate;

    private Date closedDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @ManyToMany
    @JoinTable(name = "user_projects",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> users;

    @OneToMany(mappedBy = "project")
    private List<Bug> bugs;
}
