package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserLogin,String> {


    List<UserLogin> findByPassword(String password);


}