package com.springProject.Bookora.Dto;

import java.util.List;

import com.springProject.Bookora.Entities.Eventdetails;
import com.springProject.Bookora.Entities.User;

public class BookingRetrievalResponse {

    private int bookingId;

    private UserRetrievalResponse bookingUserDetails;

    private EventRetrievalResponse bookingEventDetails;

    public BookingRetrievalResponse() {
    }

    public BookingRetrievalResponse(int bookingId, UserRetrievalResponse bookingUserDetails,
            EventRetrievalResponse bookingEventDetails) {
        this.bookingId = bookingId;
        this.bookingUserDetails = bookingUserDetails;
        this.bookingEventDetails = bookingEventDetails;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public UserRetrievalResponse getBookingUserDetails() {
        return bookingUserDetails;
    }

    public void setBookingUserDetails(UserRetrievalResponse bookingUserDetails) {
        this.bookingUserDetails = bookingUserDetails;
    }

    public EventRetrievalResponse getBookingEventDetails() {
        return bookingEventDetails;
    }

    public void setBookingEventDetails(EventRetrievalResponse bookingEventDetails) {
        this.bookingEventDetails = bookingEventDetails;
    }

    @Override
    public String toString() {
        return "BookingRetrievalResponse [bookingId=" + bookingId + ", bookingUserDetails=" + bookingUserDetails
                + ", bookingEventDetails=" + bookingEventDetails + "]";
    }


    
   

    
}
