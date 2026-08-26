package com.springProject.Bookora.Entities;

public class Userloginrequest {

    private String userName;

    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Userloginrequest() {
    }

    public Userloginrequest(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Userloginrequest [userName=" + userName + ", passWord=" + passWord + "]";
    }

    

}
