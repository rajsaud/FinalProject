package com.example.FinalProject.service;

import com.example.FinalProject.Validator.ValidatorInfo;
import com.example.FinalProject.entity.Address;
import com.example.FinalProject.entity.Role;
import com.example.FinalProject.entity.User;
import com.example.FinalProject.model.AddressModel;
import com.example.FinalProject.model.RoleModel;
import com.example.FinalProject.model.UserModel;
import com.example.FinalProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    public UserService() {

    }

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUserDetails(List<UserModel> userModels) {

        try {
            for (UserModel userModel : userModels) {//3 user
                User user = new User();
//                    if (validator.validateUserName(userModel.getFirstName(),userModel.getLastname())){
//                    user.setFirstName(userModel.getFirstName());
//                    user.setLastName(userModel.getLastname());
//                }else{
//                    return "User Name is Incorrect";
//                }
                user.setInstitude(userModel.getInstitude());
                user.setUsername(userModel.getUserName());
                user.setFirstName(userModel.getFirstName());
                user.setLastName(userModel.getLastName());
                if (ValidatorInfo.validateEmail(userModel.getEmail())) {
                    user.setEmail(userModel.getEmail());
                } else {
                    return "Email Id is Incorrect..Please use@!!";
                }
                user.setPassword(userModel.getPassword());
                List<RoleModel> roleModels = userModel.getRole();
                for (RoleModel roleModel : roleModels) {
                    Role role = new Role();
                    role.setName(roleModel.getName());
                    role.setDept(roleModel.getDept());
                    user.addRoles(role);
                }
                AddressModel addressModel = userModel.getAddress();
                Address address = new Address();
                address.setCity(addressModel.getCity());
                address.setId(addressModel.getId());
                address.setPincode(addressModel.getPincode());
                address.setLocation(addressModel.getLocation());
                user.setAddress(address);
                this.userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Error details ::" + e.getMessage());
            return e.getMessage();
        }
        return "success";

    }

    public User fetchDetailsForSelectedUser(Integer userId) {
        User user = new User();
        Optional<User> result = this.userRepository.findById(userId);
        if (result != null && !result.isEmpty()) {
            user = result.get();
        }
        return user;

    }

    public UserModel populateUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setLastName(user.getLastName());
        userModel.setFirstName(user.getFirstName());
        userModel.setPassword(user.getPassword());
        List<RoleModel> roleModels = new ArrayList<>();
        Set<Role> roleList = user.getRoles();
        for (Role role : roleList) {
            RoleModel roleModel = new RoleModel();
            roleModel.setName(role.getName());
            roleModel.setDept(role.getDept());
            roleModels.add(roleModel);

        }
        userModel.setRole(roleModels);
        return userModel;
    }

    public List<User> fetchDetailsAllUser() {
        List<User> result = (List<User>) this.userRepository.findAll();
        if (result != null && !result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public List<UserModel> populateAllUserModel(List<User> userList) {
        List<UserModel> userModels = new ArrayList<>();
        for (User user : userList) {
            UserModel userModel = new UserModel();
            userModel.setFirstName(user.getFirstName());
            userModel.setLastName(user.getLastName());
            userModel.setEmail(user.getEmail());
            userModel.setPassword(user.getPassword());
            List<RoleModel> allRoleModel = new ArrayList<>();
            Set<Role> roleList = user.getRoles();
            for (Role role : roleList) {
                RoleModel roleModel = new RoleModel();
                roleModel.setName(role.getName());
                roleModel.setDept(role.getDept());
                allRoleModel.add(roleModel);
            }
            userModel.setRole(allRoleModel);
            userModels.add(userModel);
        }
        return userModels;
    }

    public String updateUserInfo(UserModel userModel) {
        List<User> userList = this.userRepository.fetchUserByName(userModel.getFirstName(), userModel.getLastName());
        if (userList != null) {
            for (User user : userList) {
                user.setEmail(userModel.getEmail());
                user.setPassword(userModel.getPassword());
                this.userRepository.save(user);
            }
        }
        return "Success";
    }

    public Boolean deleteRecords(Integer userID) {
        this.userRepository.deleteById(userID);
        return true;
    }

    public String updateInfoBasedOnUserId(Integer userId, UserModel userModel) {
        Optional<User> result = this.userRepository.findById(userId);
        if (result != null) {
            User user = result.get();

            user.setFirstName(userModel.getFirstName());
            user.setLastName(userModel.getLastName());
            user.setEmail(userModel.getEmail());
            user.setPassword(user.getPassword());

            this.userRepository.save(user);
        }

        return "Success";
    }


}


