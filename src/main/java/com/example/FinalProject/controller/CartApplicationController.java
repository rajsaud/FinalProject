package com.example.FinalProject.controller;



import com.example.FinalProject.entity.User;
import com.example.FinalProject.model.UserModel;
import com.example.FinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartApplicationController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/firstTest") //http://localhost:8080/cart/firstTest?id=5
    public String getData() {
        return "Hello";
    }

    @GetMapping(value = "/secondTest") //http://localhost:8080/cart/secondTest
    public String getSecondData() {
        return "Hello Second";
    }

    //save data to database
    @PostMapping(value = "/saveData")//http://localhost:8081/cart/saveData
    public String saveCustomerRecord(@RequestBody List<UserModel> userModels) {
        System.out.println("The list size is ::" + userModels.size());
        String result = this.userService.saveUserDetails(userModels);

        return result;

    }

    //Fetch the data from database//http://localhost:8080/cart/fetchUserInfo
    @GetMapping(value = "/fetchUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel fetchRecords(@RequestParam(name = "userId") Integer userId) {
        User retrievedUser = this.userService.fetchDetailsForSelectedUser(userId);
        UserModel finalUserModel = this.userService.populateUserModel(retrievedUser);
        return finalUserModel;

    }

    //Fetch all the data from database//http://localhost:8080/cart/fetchUserAllinfo
    @GetMapping(value = "/fetchUserAllinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserModel> fetchAllUserInfo() {
        List<User> userList = this.userService.fetchDetailsAllUser();
        List<UserModel> userModels = this.userService.populateAllUserModel(userList);
        return userModels;

    }

    //Updating records
    @PutMapping("/updateUserInfo")//http://localhost:8080/cart/updateUserInfo
    public String updateUserInfo(@RequestBody UserModel userModel) {
        String result = this.userService.updateUserInfo(userModel);
        return result;
    }

    //Delete records
    @DeleteMapping("/deleteRecords")//http://localhost:8080/cart/deleteRecords
    public Boolean deleteRecords(@RequestParam("userId") Integer userId) {
        return this.userService.deleteRecords(userId);
    }

    @PutMapping("/updateInfoBasedOnUserId")//http://localhost:8080/cart/updateInfoBasedOnUserId
    public String updateInfoBasedOnUserId(@RequestParam("userId") Integer userId, @RequestBody UserModel userModel) {
        String result = this.userService.updateInfoBasedOnUserId(userId, userModel);
        return result;
    }
}


