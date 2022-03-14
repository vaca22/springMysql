package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Autowired
    private KafkaTemplate<Object, Object> template;

    @GetMapping("/send/{input}")
    public void sendFoo(@PathVariable String input) {
        this.template.send("topic_input", input);
    }
    @KafkaListener(id = "webGroup", topics = "topic_input")
    public void listen(String input) {
        System.out.println("input value: {}" +input);
    }
    /**
     * 查
     * @return
     */
    @GetMapping(value = "/list")
    public List<UserLogin> getUserList(){
        return userInfoRepository.findAll();
    }

    /**
     * 增
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/addUser")
    public UserLogin addUser(@RequestParam("phone") String phone,
                             @RequestParam("password") String password){
        UserLogin user = new UserLogin();
        user.setPhone(phone);
        user.setPassword(password);
        return userInfoRepository.save(user);
    }


    @PostMapping(value = "/fuck")
    public UserLogin addUser(@RequestBody UserLogin nx){

        return userInfoRepository.save(nx);
    }

    /**
     * 改
     * @param id
     * @param username
     * @param password
     * @return
     */
    @PutMapping(value = "updUser/{phone}")
    public UserLogin updUser(
                            @PathVariable("phone") String phone,
                            @RequestParam("password") String password){
        UserLogin user = new UserLogin();
        user.setPhone(phone);
        user.setPassword(password);
        return userInfoRepository.save(user);
    }

    /**
     * 删
     * @param id
     */
    @DeleteMapping(value = "delUser/{phone}")
    public void delUser(@PathVariable("phone") String phone){
        UserLogin user = new UserLogin();
        user.setPhone(phone);
        userInfoRepository.delete(user);
    }

}