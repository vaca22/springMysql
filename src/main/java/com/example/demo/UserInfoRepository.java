package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserLogin,String> {


    List<UserLogin> findByPassword(String password);

    List<UserLogin> findByPhone(String phone);

    @Query("SELECT m FROM UserLogin m")
    List<UserLogin> retrieveByName();

    @Query("SELECT m FROM UserLogin m WHERE m.password=:password")
    List<UserLogin> retrieveByName2(String password);
}