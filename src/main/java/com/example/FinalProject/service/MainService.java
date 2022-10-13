package com.example.FinalProject.service;

import com.example.FinalProject.Validator.ValidateInformation;
import com.example.FinalProject.entity.Address;
import com.example.FinalProject.entity.Customer;
import com.example.FinalProject.entity.FoodItems;
import com.example.FinalProject.model.AddressModel;
import com.example.FinalProject.model.CustomerModel;
import com.example.FinalProject.model.FoodItemsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.FinalProject.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MainService {
    @Autowired
    private CustomerRepository customerRepository;

    public String saveCustomerData(List<CustomerModel> customerModels){
    try {


        for (CustomerModel customerModel:customerModels) {
            Customer customer = new Customer();
            ValidateInformation validateInformation = new ValidateInformation();
            customer.setCustId(customerModel.getCustId());
            customer.setInstitute(customerModel.getInstitute());
            if (validateInformation.validateName(customerModel.getCustName())){
                customer.setCustName(customerModel.getCustName());
            }else {
                return "customername is null";
            }

            if (validateInformation.validateMobileNumber(customerModel.getMobileNumber())){
                customer.setMobileNumber(customerModel.getMobileNumber());
            }else {
                return "mobile is not valid";
            }
            if (validateInformation.validateCountry(customerModel.getCountry())){
                customer.setCountry(customerModel.getCountry());
            }else {
                return "count is null";
            }
            List<FoodItemsModel> foodItemsModels = customerModel.getList();
            for (FoodItemsModel foodItemsModel : foodItemsModels) {
                FoodItems foodItems = new FoodItems();
                foodItems.setItem_id(foodItemsModel.getItemId());
                if (validateInformation.validateItemName(foodItemsModel.getName())){
                    foodItems.setName(foodItemsModel.getName());
                }else {
                    return "itemName is null";
                }
               if (validateInformation.validateQuantity(foodItemsModel.getQuantity())){
                   foodItems.setQuantity(foodItemsModel.getQuantity());
               }else {
                   return "provide atleast one item";
               }
                customer.addItems(foodItems);
            }
            AddressModel addressModel = customerModel.getAddress();
            Address address = new Address();
            address.setCity(addressModel.getCity());
            address.setId(addressModel.getId());
            address.setPincode(addressModel.getPincode());
            address.setLocation(addressModel.getLocation());
            customer.setAddress(address);
            this.customerRepository.save(customer);
        }

        }catch (Exception e) {
        System.err.println("Exceptiom msg "+e.getMessage());
    }
       return "data saved";
    }


    public String updateInfoBasedcustId(Integer custId, CustomerModel customerModel) {
        Optional<Customer> result = this.customerRepository.findById(custId);
        if (result !=null){
            Customer customer = result.get();
            customer.setCustName(customerModel.getCustName());
            customer.setMobileNumber(customerModel.getMobileNumber());
            customer.setCountry(customerModel.getCountry());
            this.customerRepository.save(customer);
        }
        return "Save Customer data Success";
    }

    public Boolean deleteRecords(Integer custId) {
        this.customerRepository.deleteById(custId);
        return true;
    }

    public Customer fetchDetailsForSelectedCustomer(Integer custId) {
        Customer customer = new Customer();
        Optional<Customer> result = this.customerRepository.findById(custId);
        if (result != null && !result.isEmpty()){
            customer= result.get();
        }
        return customer;
    }

    public CustomerModel populateCustomerModel(Customer customer) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustName(customer.getCustName());
        customerModel.setCustId(customer.getCustId());
        customerModel.setMobileNumber(customer.getMobileNumber());
        customerModel.setCountry(customer.getCountry());
        List<FoodItemsModel>foodItemsModels = new ArrayList<>();
        Set<FoodItems>foodItemsList = (Set<FoodItems>) customer.getList();
        for (FoodItems foodItems:foodItemsList){
            FoodItemsModel foodItemsModel = new FoodItemsModel();
            foodItemsModel.setName(foodItems.getName());
            foodItemsModel.setItemId(foodItems.getItem_id());
            foodItemsModel.setQuantity(foodItems.getQuantity());
            customer.addItems(foodItems);

        }
        customerModel.setList(foodItemsModels);
        return customerModel;

    }
}
