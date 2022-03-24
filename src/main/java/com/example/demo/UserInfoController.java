package com.example.demo;

import com.example.demo.requestback.RegisterReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cloud-napi/v1")
public class UserInfoController {


    private UserInfoRepository userInfoRepository;

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }





    @PostMapping(value = "/register")
    public RegisterReturn register(@RequestBody UserLogin user) {
        System.out.println("fuckyou");
        String phone=user.getPhone();
        List<String> n1 = userInfoRepository.findByPhone(phone);

        if (n1.isEmpty()) {
            userInfoRepository.save(user);
            return new RegisterReturn(1, "register success", null);
        } else {
            return new RegisterReturn(-1, "the phone has been registered", null);
        }

    }



    @PostMapping(value = "/login")
    public RegisterReturn login(@RequestBody UserLogin user) {
        System.out.println("login");
        String phone=user.getPhone();

        String n1 = userInfoRepository.findByPhonePassword(phone);
        if(n1==null){
            return new RegisterReturn(2, "the phone has been registered", null);
        }
        if(n1.isEmpty()){
            return new RegisterReturn(3, "the phone has been registered", null);
        }
        if (!n1.equals(user.getPassword())) {
            return new RegisterReturn(4, "the phone has been registered", null);
        }
        return new RegisterReturn(1, "register success", null);
    }


}