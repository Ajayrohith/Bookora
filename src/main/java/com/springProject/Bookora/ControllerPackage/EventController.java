package com.springProject.Bookora.ControllerPackage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.Bookora.Entities.Apiresponse;
import com.springProject.Bookora.Entities.Eventdetails;
import com.springProject.Bookora.ServiceDetails.Servicelayer;

@RestController
@RequestMapping("/event")
public class EventController {

    private Servicelayer service;

    public EventController(Servicelayer service)
    {
        this.service = service;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<Apiresponse> AddnewEventPool(@RequestBody Eventdetails eventDetails)
    {
        
        try{
            Eventdetails createdEvent = service.addnewEvent(eventDetails);
            System.out.println(createdEvent.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Apiresponse(0, "New event registered successfully"));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Apiresponse(0, "Exception in creating a new event"));
        }
    }

}
