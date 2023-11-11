package org.example.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.payloads.request.Auth;
import org.example.payloads.request.Booking;
import org.example.payloads.request.Bookingdates;
import org.example.payloads.response.BookingResponse;

//Serialization and Deserialization

public class PayloadManager {
    //Java object to JSON converter
    ObjectMapper objectMapper;

    public String createPayload() throws JsonProcessingException {
        objectMapper= new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Abdul");
        booking.setLastname("Rashid");
        booking.setTotalprice(800);
        booking.setAdditionalneeds("breakfast");
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("05/11/2023");
        bookingdates.setCheckout("11/11/2023");
        booking.setBookingdates(bookingdates);
        String payload =objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public BookingResponse JsontoObject(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        BookingResponse bookingResponse = objectMapper.readValue(jsonString,BookingResponse.class);
        return bookingResponse;

    }
    public Booking JsontoObjectPut(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking bookingResponse = objectMapper.readValue(jsonString,Booking.class);
        return bookingResponse;


    }
    public String UpdatePayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Rashid");
        booking.setTotalprice(800);
        booking.setAdditionalneeds("breakfast");
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("5/11/2023");
        bookingdates.setCheckout("11/11/2023");
        booking.setBookingdates(bookingdates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public String settheToken() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);
    }
}
