package org.example.actions;
import static org.testng.Assert.assertEquals;

import io.restassured.response.Response;

public class Assertactions {
    public void verifystatuscode(Response response){
        assertEquals(String.valueOf(response.getStatusCode()).startsWith("20"),true,
         "value of status code is"+response.getStatusCode());
    }
    public void verifyresponsebody(String actual,String expected,String description){
        assertEquals(actual,expected,description);
    }
    public void verifyresponsebody(float actual,float  expected,String  description) {
        assertEquals(actual, expected, description);
    }
    public void verifyresponsebody(double actual,double expected,String description) {
        assertEquals(actual, expected, description);
    }
    public void verifyresponsebody(int actual,int expected,String description) {
        assertEquals(actual, expected, description);
    }
    public void verifyresponsebody(boolean actual,boolean expected,String description) {
        assertEquals(actual, expected, description);
    }
}
