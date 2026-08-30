package com.springProject.Bookora.ServiceDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.springProject.Bookora.DaoDetails.DaoInterface;
import com.springProject.Bookora.Dto.BookingRetrievalResponse;
import com.springProject.Bookora.Dto.EventRetrievalResponse;
import com.springProject.Bookora.Dto.UserRetrievalResponse;
import com.springProject.Bookora.Dto.Userloginrequest;
import com.springProject.Bookora.Entities.Booking;
import com.springProject.Bookora.Entities.Eventdetails;
import com.springProject.Bookora.Entities.User;
import com.springProject.Bookora.ExceptionPackage.EventDetailsException;
import com.springProject.Bookora.ExceptionPackage.UserdetailsException;

import jakarta.transaction.Transactional;

@Service
public class Servicelayer {

    private DaoInterface daoobject;

    private JsonMapper jsonmapper;

    private PasswordEncoder passwordEncoder;

    public Servicelayer(DaoInterface daoobject, JsonMapper jsonmapper, PasswordEncoder passwordEncoder)
    {
        this.daoobject = daoobject; 
        this.jsonmapper = jsonmapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createNewUser(User sUser)
    {
        User existinguser = daoobject.findUserbyUsername(sUser.getUserName());
        if(existinguser != null)
        {
            throw new UserdetailsException("User already exists");
        }
        else{
             String encodedPassword = passwordEncoder.encode(sUser.getPassWord());
             sUser.setPassWord(encodedPassword);
             return daoobject.updateuser(sUser);
        }
       
    }

    public boolean validateUser(Userloginrequest suser)
    {
        String inputPassword = suser.getPassWord();
        String passWordfromDb = daoobject.retrieveUserPassword(suser.getUserName());


        if(passWordfromDb == null)
        {
            return false;
        }
        return passwordEncoder.matches(inputPassword, passWordfromDb);
    }

    public List<UserRetrievalResponse> getAllusers()
    {

        List<User> tempUserList = daoobject.retriveuserList();

        List<UserRetrievalResponse> responseList = new ArrayList<>();

        for(User user : tempUserList )
        {
            UserRetrievalResponse dto = new UserRetrievalResponse(user.getUserId(), user.getUserName(), user.getAge());
            responseList.add(dto);
        }

        return responseList;
    }
     //////////////////////Start Business Logic for EventDetails Part /////////////////////////////
     @Transactional
     public Eventdetails addnewEvent(Eventdetails event)
     {
        return daoobject.addnewEvent(event);
     }
     

     public Eventdetails findEventbyid(int eventId)
     {
        return daoobject.findEventbyId(eventId);
     }
  
    @Transactional
    public Eventdetails updateEventDetails(Map<String, Object> patchPayload, int eventId)
     {
        Eventdetails tempEvent = daoobject.findEventbyId(eventId);

        Eventdetails PatchEventDetails = null;

        if(tempEvent == null)
        {
            throw new EventDetailsException("The given evenId not found");
        }

        if(patchPayload.containsKey("id"))
        {
            throw new EventDetailsException("The id cannot be passed in the payload");
        }

        try {
             PatchEventDetails = jsonmapper.updateValue(tempEvent, patchPayload);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return daoobject.addnewEvent(PatchEventDetails);
     }
     //////////////////////End Business Logic for EventDetails Part /////////////////////////////

     ///  //////////////////////Start Business Logic for Bookings Part /////////////////////////////
     @Transactional
     public Booking createNewBooking(int userId,int eventPoolId)
     {
        User tempuser = daoobject.finduserbyuserId(userId);

        System.out.println("Logger 1 : "+tempuser.toString());

        if(tempuser == null)
        {
            throw new UserdetailsException("The user does not exist");
        }

        Eventdetails tempevent = daoobject.findEventbyId(eventPoolId);

         System.out.println("Logger 2 : "+tempevent.toString());

         if(tempevent == null)
        {
            throw new RuntimeException("The event details does not exist");
        }

        if(tempevent.getTicketsAvailable() == 0)
        {
            throw new RuntimeException("No tickets currently available for the event");
        }

        Booking booking = new Booking();
        tempuser.addBookingstoUser(booking);
        tempevent.addBookingstoEvent(booking);

        tempevent.setTicketsAvailable(tempevent.getTicketsAvailable() - 1);


        return daoobject.createNewbooking(booking);
     }

     public BookingRetrievalResponse findBookingDetails(int bookingId)
     {

        Booking tempBooking = daoobject.findBookingbyId(bookingId);

          if(tempBooking == null)
        {
            return null;
        }

        UserRetrievalResponse userDetails = new UserRetrievalResponse
        (tempBooking.getUser().getUserId(),
         tempBooking.getUser().getUserName(), tempBooking.getUser().getAge());

         EventRetrievalResponse eventDetails = new EventRetrievalResponse
         (tempBooking.getEvents().getEventpoolId(),
          tempBooking.getEvents().getEventName(),
           tempBooking.getEvents().getTicketType(), 
           tempBooking.getEvents().getDescription(),
            tempBooking.getEvents().getPrice());

        return new BookingRetrievalResponse(bookingId, userDetails,eventDetails );
     }
  
     //////////////////////End Business Logic for Bookings Part /////////////////////////////
}
