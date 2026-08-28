package com.springProject.Bookora.DaoDetails;

import java.util.List;

import com.springProject.Bookora.Entities.Booking;
import com.springProject.Bookora.Entities.Eventdetails;
import com.springProject.Bookora.Entities.User;

public interface DaoInterface {

    public User updateuser(User user);

    public User findUserbyUsername(String name);

    public User finduserbyuserId(int id);

    public String retrieveUserPassword(String username);

    public List<User> retriveuserList();

    public Eventdetails addnewEvent(Eventdetails event);

    public Eventdetails findEventbyId(int eventID);

    public Booking createNewbooking(Booking booking);

    public Booking findBookingbyId(int bookingId);

}
