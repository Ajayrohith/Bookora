package com.springProject.Bookora.ExceptionPackage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springProject.Bookora.Dto.Apiresponse;

@ControllerAdvice
public class GlobalexceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Apiresponse> RuntimeexceptionHandler(RuntimeException e)
    {
        System.out.println("Inside global runtime exception exception handling");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Apiresponse(-1, e.getMessage()));
    }

    @ExceptionHandler(UserdetailsException.class)
    public ResponseEntity<Apiresponse> UsersExceptionHandler(UserdetailsException e)
    {
         System.out.println("Inside global UsersExceptionHandler exception handling");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Apiresponse(-1, e.getMessage()));
    }

     @ExceptionHandler(EventDetailsException.class)
    public ResponseEntity<Apiresponse> EventsExceptionHandler(EventDetailsException e)
    {
         System.out.println("Inside global EventDetailsException exception handling");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Apiresponse(-1, e.getMessage()));
    }

}


