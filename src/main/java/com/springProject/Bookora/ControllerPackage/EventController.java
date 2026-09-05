package com.springProject.Bookora.ControllerPackage;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.Bookora.Dto.Apiresponse;
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

    // @GetMapping("/findEvent/{eventId}")
    // public Eventdetails findEvent(@PathVariable int eventId)
    // {
    //     return service.findEventbyid(eventId);
    // }

    @PatchMapping("/updateEvent/{eventId}")
    public ResponseEntity<Apiresponse> updateEvent(@RequestBody Map<String,Object> patchPayload, @PathVariable int eventId)
    {
       Eventdetails PatchedEvent = service.updateEventDetails(patchPayload,eventId);
       System.out.println("The patchedEvent : "+ PatchedEvent.toString());
       return ResponseEntity.status(HttpStatus.CREATED).body(new Apiresponse(0, "Event Updated successfully"));
    }

}
