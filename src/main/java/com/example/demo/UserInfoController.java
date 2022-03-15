package com.example.demo;

import com.example.demo.requestback.RegisterReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloud-napi/v1")
public class UserInfoController {


    private UserInfoRepository userInfoRepository;

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }


    private KafkaTemplate<Object, Object> template;


    @Autowired
    public void setTemplate(KafkaTemplate<Object, Object> template) {
        this.template = template;
    }


    @PostMapping(value = "/register")
    public RegisterReturn register(@RequestParam("phone") String phone,
                                   @RequestParam("password") String password) {
        UserLogin user = new UserLogin();
        user.setPhone(phone);
        user.setPassword(password);
        List<String> n1 = userInfoRepository.findByPhone(phone);

        if (n1.isEmpty()) {
            userInfoRepository.save(user);
            return new RegisterReturn(1, "register success", null);
        } else {
            return new RegisterReturn(-1, "the phone has been registered", null);
        }

    }


    @GetMapping("/send/{input}")
    public void sendFoo(@PathVariable String input) {
        this.template.send("fuck", input);
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
        List<UserLogin>  d= userInfoRepository.findByPassword("sdf");
        return d.get(0);
    }

    @GetMapping(value = "/fuck")
    public List<UserLogin> addUser(){
        List<UserLogin>  d= userInfoRepository.findByPassword("sdf");
        return userInfoRepository.retrieveByName2("sdf");
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