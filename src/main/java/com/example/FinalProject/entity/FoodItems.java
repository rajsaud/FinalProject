package com.example.FinalProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "FoodItems")
public class FoodItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer item_id;

    @Column
    private String name;

    @Column
    private Integer quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
}
