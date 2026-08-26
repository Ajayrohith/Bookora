package com.springProject.Bookora.Entities;

public class BookingsRequest {

    private int userId;

    private int eventPoolId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventPoolId() {
        return eventPoolId;
    }

    public void setEventPoolId(int eventPoolId) {
        this.eventPoolId = eventPoolId;
    }

    public BookingsRequest() {
    }

    public BookingsRequest(int userId, int eventPoolId) {
        this.userId = userId;
        this.eventPoolId = eventPoolId;
    }

    @Override
    public String toString() {
        return "BookingsRequest [userId=" + userId + ", eventPoolId=" + eventPoolId + "]";
    };

    

}
