package edu.unc.vluhadia.riderequest;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Request {
    String Name;
    String destination;
    String phone;
    String time;

    public Request () {
        //default constructor required for db reference
    }


    public Request (String phone, String destination, String time) {
        this.destination = destination;
        this.phone = phone;
        this.time = time;



    }











}
