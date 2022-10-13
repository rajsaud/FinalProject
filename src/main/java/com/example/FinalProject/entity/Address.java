package com.example.FinalProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = true)
    private Long id;

    @Column
    private String city;
    @Column
    private String pincode;
    @Column
    private String location;
    @OneToOne (mappedBy = "address")
    @JoinColumn (nullable = true)
    private Customer customer;
    @OneToOne(mappedBy = "address")
    private User user;
}
