package com.example.FinalProject.Validator;

public class ValidateInformation {

    public Boolean validateMobileNumber(Long mobileNumber){
        String number = String.valueOf(mobileNumber);
        if (number !=null && number.length()==10){
            return true;
        }else {
            return false;
        }
    }
    public Boolean validateCountry (String country){
        if (country != null){
            return true;

        }else {
            return false;
        }
    }
    public Boolean validateName(String name){
        if (name != null){
            return true;
        }else {
            return false;
        }
    }
    public Boolean validateItemName (String itemName){
        if (itemName != null){
            return true;
        }else {
            return false;
        }
    }
    public Boolean validateQuantity (Integer quantity){
        if (quantity != null){
            return true;
        }else {
            return false;
        }
    }
}
