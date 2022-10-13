package com.example.FinalProject.model;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AddressModel address;
    private String userName;
    private String institude;
    private List<RoleModel> role;



}

