package com.example.FinalProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer custId;

    @Column(name = "cust_name")
    private String custName;

    @Column (name = "moblile_number")
    private Long mobileNumber;

    @Column (name = "country")
    private String country;

    @Column(name = "institute")
    private String institute;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true,name = "add_id",referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    List<FoodItems> list = new ArrayList<FoodItems>();
    public void addItems(FoodItems foodItems){
        this.list.add(foodItems);
        foodItems.setCustomer(this);
    }


}
