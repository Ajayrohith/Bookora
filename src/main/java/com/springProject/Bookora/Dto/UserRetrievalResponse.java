package com.springProject.Bookora.Dto;

import java.io.Serializable;

public class UserRetrievalResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;

    private String userName;

    private int age;

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

    public UserRetrievalResponse() {
    }

    public UserRetrievalResponse(int userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserRetrievalResponse [userId=" + userId + ", userName=" + userName + ", age=" + age + "]";
    }

    

}
