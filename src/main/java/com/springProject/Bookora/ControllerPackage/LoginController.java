package com.springProject.Bookora.ControllerPackage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.Bookora.Entities.User;
import com.springProject.Bookora.ServiceDetails.Servicelayer;

@RestController
@RequestMapping("/login")
public class LoginController {

    private Servicelayer service;

    public LoginController()
    {
        
    }

    @PostMapping("/createuser")
    public User CreateUser()
    {
        return null;
    }

}
