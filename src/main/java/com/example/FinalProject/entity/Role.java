package com.example.FinalProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="Role")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleID;

    @Column (name="role_name")
    private String name;

    @Column (name="dept_name")
    private String dept;

    @ManyToMany (mappedBy = "roles",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set <User> list= new HashSet<User>();


}

