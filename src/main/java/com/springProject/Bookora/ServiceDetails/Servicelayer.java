package com.springProject.Bookora.ServiceDetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springProject.Bookora.DaoDetails.DaoInterface;
import com.springProject.Bookora.Dto.UserRetrievalResponse;
import com.springProject.Bookora.Dto.Userloginrequest;
import com.springProject.Bookora.Entities.Booking;
import com.springProject.Bookora.Entities.Eventdetails;
import com.springProject.Bookora.Entities.User;

import jakarta.transaction.Transactional;

@Service
public class Servicelayer {

    private DaoInterface daoobject;

    public Servicelayer(DaoInterface daoobject)
    {
        this.daoobject = daoobject;
    }

    @Transactional
    public User createNewUser(User sUser)
    {
        User existinguser = daoobject.findUserbyUsername(sUser.getUserName());
        if(existinguser != null)
        {
            throw new RuntimeException("User already exists");
        }
        else{
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
        return inputPassword.equals(passWordfromDb);
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
     
  
     //////////////////////End Business Logic for EventDetails Part /////////////////////////////

     ///  //////////////////////Start Business Logic for Bookings Part /////////////////////////////
     @Transactional
     public Booking createNewBooking(int userId,int eventPoolId)
     {
        User tempuser = daoobject.finduserbyuserId(userId);

        System.out.println("Logger 1 : "+tempuser.toString());

        if(tempuser == null)
        {
            throw new RuntimeException("The user does not exist");
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
  
     //////////////////////End Business Logic for Bookings Part /////////////////////////////
}
