package org.example.enpoints;

import org.example.utils.PropertyReaderUtil;

public class APIConstants {

    //public static String Base_Url = "https://restful-booker.herokuapp.com";

    public static String Base_Url;
    public static String Create_Booking;
    public static String Update_Booking;

    static {
        try {
            Base_Url = PropertyReaderUtil.readkey("url");
            Create_Booking = PropertyReaderUtil.readkey("Create_Booking");
            Update_Booking = PropertyReaderUtil.readkey("Update_Booking");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //public static String Create_Booking = "/booking";
   // public static String Update_Booking ="/booking";
}
