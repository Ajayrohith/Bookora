package com.springProject.Bookora.ControllerPackage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.Bookora.Entities.Apiresponse;
import com.springProject.Bookora.Entities.User;
import com.springProject.Bookora.Entities.Userloginrequest;
import com.springProject.Bookora.ServiceDetails.Servicelayer;

@RestController
@RequestMapping("/login")
public class LoginController {

    private Servicelayer service;

    public LoginController(Servicelayer service)
    {
        this.service = service;
    }

    @PostMapping("/createuser")
    public ResponseEntity<Apiresponse> CreateUser(@RequestBody User user)
    {
         try{
            User userdetails = service.createNewUser(user);       
         System.out.println(userdetails.toString());
         return ResponseEntity.status(HttpStatus.CREATED).body(new Apiresponse(0, "User created successfully"));
         }
         catch(Exception e)
         {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Apiresponse(-1, "User already exists"));
         }
        
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Apiresponse> validateuser(@RequestBody Userloginrequest user)
    {
        if(service.validateUser(user))
        {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Apiresponse(0, "User password validation succesfull"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Apiresponse(0, "Kindly re verify the credentials"));
        }
       // return null;
    }



}
