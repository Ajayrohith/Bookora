package com.springProject.Bookora.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_ticket_details")
public class Eventdetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_pool_id")
    private int EventpoolId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "description")
    private String description;

    @Column(name = "ticket_type")
    private String ticketType;

    @Column(name = "tickets_available")
    private int ticketsAvailable;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "events",cascade = CascadeType.ALL)
    private List<Booking> bookingDetails;

    public Eventdetails() {
    }

    public Eventdetails(String eventName, String description, String ticketType, int ticketsAvailable, int price) {
        this.eventName = eventName;
        this.description = description;
        this.ticketType = ticketType;
        this.ticketsAvailable = ticketsAvailable;
        this.price = price;
    }

    public int getEventpoolId() {
        return EventpoolId;
    }

    public void setEventpoolId(int eventpoolId) {
        EventpoolId = eventpoolId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    

    @Override
    public String toString() {
        return "Eventdetails [EventpoolId=" + EventpoolId + ", eventName=" + eventName + ", description=" + description
                + ", ticketType=" + ticketType + ", ticketsAvailable=" + ticketsAvailable + ", price=" + price + "]";
    }

    public List<Booking> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<Booking> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    
    public void addBookingstoEvent(Booking tempBooking2)
    {
        if(bookingDetails == null)
        {
            System.out.println("Currently the bookings are null for the user hence creating a new List");
            bookingDetails = new ArrayList<>();
        }
        bookingDetails.add(tempBooking2);
        tempBooking2.setEvents(this);
    }
    

    


}
