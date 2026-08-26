package com.springProject.Bookora.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    // @Column(name = "user_id")
    // private int user_id;

    // @Column(name = "ticket_pool_id")
    // private int ticketPoolId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_pool_id")
    private Eventdetails events;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Eventdetails getEvents() {
        return events;
    }

    public void setEvents(Eventdetails events) {
        this.events = events;
    }

    public Booking() {
    }

    public Booking(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", user=" + user + ", events=" + events + "]";
    }

    

}
