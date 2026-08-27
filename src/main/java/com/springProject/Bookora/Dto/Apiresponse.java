package com.springProject.Bookora.Dto;

public class Apiresponse {

    private int CustomResponseCode;

    private String Response;

    public Apiresponse() {
    }

    public Apiresponse(int customResponseCode, String response) {
        CustomResponseCode = customResponseCode;
        Response = response;
    }

    public int getCustomResponseCode() {
        return CustomResponseCode;
    }

    public void setCustomResponseCode(int customResponseCode) {
        CustomResponseCode = customResponseCode;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    @Override
    public String toString() {
        return "Apiresponse [CustomResponseCode=" + CustomResponseCode + ", Response=" + Response + "]";
    }

    

}
