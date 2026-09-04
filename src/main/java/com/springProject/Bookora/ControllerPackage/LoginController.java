package com.springProject.Bookora.ControllerPackage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.Bookora.Dto.Apiresponse;
import com.springProject.Bookora.Dto.UserRetrievalResponse;
import com.springProject.Bookora.Dto.Userloginrequest;
import com.springProject.Bookora.Entities.User;
import com.springProject.Bookora.ServiceDetails.JwtService;
import com.springProject.Bookora.ServiceDetails.Servicelayer;

@RestController
@RequestMapping("/login")
public class LoginController {

    private Servicelayer service;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    public LoginController(
            Servicelayer service,
            JwtService jwtService,
            AuthenticationManager authenticationManager)
    {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/createuser")
    public ResponseEntity<Apiresponse> CreateUser(@RequestBody User user)
    {
        User userdetails = service.createNewUser(user);       
        System.out.println(userdetails.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(new Apiresponse(0, "User created successfully"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Apiresponse> validateuser(@RequestBody Userloginrequest user)
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUserName(),
                            user.getPassWord()));

            String token = jwtService.generateToken(user.getUserName());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Apiresponse(0, "User password validation successful", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Apiresponse(-1, "Invalid username or password"));
        }
    }

    @GetMapping("/userlist")
    public ResponseEntity<List<UserRetrievalResponse>> retrieveUserList()
    {
        List<UserRetrievalResponse> tempUsers = service.getAllusers();
        return new ResponseEntity<>(tempUsers,HttpStatus.OK);
    }


}
