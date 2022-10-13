package com.example.FinalProject.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CustomerModel {
    private String custName;
    private Integer custId;
    private Long mobileNumber;
    private String country;
    private String institute;
    private AddressModel address;
    private List<FoodItemsModel>list;

}
