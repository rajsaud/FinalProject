package com.example.FinalProject.repository;

import com.example.FinalProject.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    @Query("select u from User u where u.firstName = ?1 and u.lastName = ?2")
    List <User> fetchUserByName(String firstName , String lastName);


}

