package com.springProject.Bookora.ControllerPackage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.Bookora.Entities.Apiresponse;
import com.springProject.Bookora.Entities.Booking;
import com.springProject.Bookora.Entities.BookingsRequest;
import com.springProject.Bookora.ServiceDetails.Servicelayer;

@RestController
@RequestMapping("/booking")
public class BookingsController {

    private Servicelayer service;

    public BookingsController(Servicelayer service)
    {
        this.service = service;
    }

    @PostMapping("/createbooking")
    public ResponseEntity<Apiresponse> createNewBooking(@RequestBody BookingsRequest bookingRequest)
    {
        try{
            Booking tempBooking = service.createNewBooking(bookingRequest.getUserId(),bookingRequest.getEventPoolId());
            System.out.println("The controller booking details : "+ tempBooking.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Apiresponse(0, "The booking has been created"));

        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Apiresponse(0, "Exception in creating booking"));

        }
    }

}
