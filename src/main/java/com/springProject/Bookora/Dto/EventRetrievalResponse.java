package com.springProject.Bookora.Dto;

public class EventRetrievalResponse {

    private int eventPoolId;

    private String eventName;

    private String ticketType;

    private String description;

    private int ticketPrice;

    public EventRetrievalResponse() {
    }

    public EventRetrievalResponse(int eventPoolId, String eventName, String ticketType, String description,
            int ticketPrice) {
        this.eventPoolId = eventPoolId;
        this.eventName = eventName;
        this.ticketType = ticketType;
        this.description = description;
        this.ticketPrice = ticketPrice;
    }

    public int getEventPoolId() {
        return eventPoolId;
    }

    public void setEventPoolId(int eventPoolId) {
        this.eventPoolId = eventPoolId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "EventRetrievalResponse [eventPoolId=" + eventPoolId + ", eventName=" + eventName + ", ticketType="
                + ticketType + ", description=" + description + ", ticketPrice=" + ticketPrice + "]";
    }

    
}
