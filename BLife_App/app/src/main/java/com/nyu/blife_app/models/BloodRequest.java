package com.nyu.blife_app.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;


import java.util.Date;

/**
 * Created by Yeshwant on 01/04/2015.
 */

@ParseClassName("BloodRequest")
public class BloodRequest extends ParseObject {
    public BloodRequest(){

    }

    /**getter methods */

    public String getReceiverName(){
        return getString("receiverName");
    }

    public String getBloodGroup(){
        return getString("bloodGroup");
    }

    public String getLocation(){
        return getString("location");
    }

    public String getCity(){
        return getString("city");
    }

    public String getMessage(){
        return getString("message");
    }

    public Date getRequiredBefore(){
        return getDate("requiredBefore");
    }

    public int getCellNumber(){
        return getInt("cellNumber");
    }

    public String getRequestStatus(){
        return getString("requestStatus");
    }

    public int getUserId(){
        return getInt("userId");

    }

    /**setter methods */
    public void setReceiverName (String receiverName){
        put("receiverName", receiverName);
    }

    public void setBloodGroup(String bloodGroup){
        put("bloodGroup", bloodGroup);
    }

    public void setLocation(String location){
        put("location", location);
    }

    public void setCity(String city){
        put("city", city);
    }

    public void setMessage(String message){
        put("message", message);
    }

    public void setRequiredBefore(Date requiredBefore){
        put("requiredBefore", requiredBefore);
    }

    public void setCellNumber(int cellNumber){
        put("cellNumber", cellNumber);
    }

    public void setRequestStatus(String requestStatus){
        put("requestStatus", requestStatus);
    }

    public void setUserId(int userId){
        put("userId", userId);
    }

}

