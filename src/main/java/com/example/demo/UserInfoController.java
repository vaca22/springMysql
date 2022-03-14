package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 查
     * @return
     */
    @GetMapping(value = "/list")
    public List<UserInfo> getUserList(){
        UserInfo user = new UserInfo();
        user.setUsername("fruck");
        user.setId(324);
        user.setPassword("yopu");
        userInfoRepository.save(user);
        return userInfoRepository.findAll();
    }

    /**
     * 增
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/addUser")
    public UserInfo addUser(@RequestParam("username") String username,
                            @RequestParam("password") String password){
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(password);
        return userInfoRepository.save(user);
    }

    /**
     * 改
     * @param id
     * @param username
     * @param password
     * @return
     */
    @PutMapping(value = "updUser/{id}")
    public UserInfo updUser(@PathVariable("id") Integer id,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password){
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return userInfoRepository.save(user);

    }

    /**
     * 删
     * @param id
     */
    @DeleteMapping(value = "delUser/{id}")
    public void delUser(@PathVariable("id") Integer id){
        UserInfo user = new UserInfo();
        user.setId(id);
        userInfoRepository.delete(user);
    }

}