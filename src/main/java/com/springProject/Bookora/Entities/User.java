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
@Table(name = "userdetails")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String passWord;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Booking> bookingDetails;


    public User() {
    }

    public User(String userName, int age, String passWord) {
        this.userName = userName;
        this.age = age;
        this.passWord = passWord;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", age=" + age + ", passWord=" + passWord + "]";
    }

    public List<Booking> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<Booking> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public void addBookingstoUser(Booking tempBooking)
    {
        if(bookingDetails == null)
        {
            System.out.println("Currently the bookings are null for the user hence creating a new List");
            bookingDetails = new ArrayList<>();
        }
        bookingDetails.add(tempBooking);
        tempBooking.setUser(this);
    }
    

    


}
