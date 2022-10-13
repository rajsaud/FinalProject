package com.example.FinalProject.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name= "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String institude;
    @Column
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    /*@Onetomany(mappeBy= "user",cascade= CascadeType.ALL, orphanRemoval = true,fetch= FetchType.LAZY)
    private Set<Role> roles = new HashSet<role>();
     */
    @ManyToMany (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name="user_ID")},
            inverseJoinColumns = {@JoinColumn(name="role_ID")}
    )
    private Set<Role> roles = new HashSet<Role>();

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "user_address",
            joinColumns = @JoinColumn(name = "user_null"),
            inverseJoinColumns = @JoinColumn(name = "address_city"))


    /*public void addRoles(Role role){
        this.roles.add(role);
        role.setUser(this);
        }*/
    public void addRoles(Role role){
        this.roles.add(role);
        role.getList().add(this);
    }

}

