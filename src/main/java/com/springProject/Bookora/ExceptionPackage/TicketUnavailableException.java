package com.springProject.Bookora.ExceptionPackage;

public class TicketUnavailableException extends RuntimeException {

    public TicketUnavailableException(String message) {
        super(message);
    }
}
