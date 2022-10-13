package com.example.FinalProject.controller;


import com.example.FinalProject.entity.Customer;
import com.example.FinalProject.model.CustomerModel;
import com.example.FinalProject.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/Final")
public class ControllerMain {
    @Autowired
    private MainService mainService;

    @GetMapping("/test")//http://localhost:8080/Final/test
    public String check() {
        return "Final Project";
    }

    @PostMapping(value = "/savedata")//http://localhost:8080/Final/savedata
    public String saveCustomerData(@RequestBody List<CustomerModel> customerModels) {
        System.out.println("The list size is::" + customerModels.size());
        String result = this.mainService.saveCustomerData(customerModels);

        return result;
    }

    @PutMapping("/updateInfoBasedcustId")//http://localhost:8080/Final/updateInfoBasedcustId
    public String updateInfoBasedcustId(@RequestParam("custId") Integer custId, @RequestBody CustomerModel customerModel) {
        String result = this.mainService.updateInfoBasedcustId(custId, customerModel);
        return result;

    }

    @DeleteMapping("/deleteRecords")//http://localhost:8080/Final/deleteRecords
    public Boolean deleteRecords(@RequestParam("custId") Integer custId) {
        return this.mainService.deleteRecords(custId);
    }
    //Fetch the data from database
    @GetMapping(value = "/fetchCustomerInfo",produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerModel fetchRecords(@RequestParam(name = "custId")Integer custId){
        Customer retrievedCustomer =this.mainService.fetchDetailsForSelectedCustomer(custId);
        CustomerModel finalCustomerModel = this.mainService.populateCustomerModel(retrievedCustomer);
        return finalCustomerModel;
    }
}
