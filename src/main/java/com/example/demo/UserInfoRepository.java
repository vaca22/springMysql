package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserLogin,String> {


    List<UserLogin> findByPassword(String password);

    @Query("SELECT m FROM UserLogin m")
    List<UserLogin> retrieveByName();
}