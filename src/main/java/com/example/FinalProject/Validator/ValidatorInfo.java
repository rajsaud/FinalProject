package com.example.FinalProject.Validator;


public class ValidatorInfo {
    public boolean validateUserName(String firstName,String lastName){
        if (firstName !=null && lastName !=null){
            return true;
        }
        return false;

    }
    public static boolean validateEmail(String emailId){
        if (emailId!=null && emailId.contains("@")){
            return true;
        }
        return false;
    }
}

