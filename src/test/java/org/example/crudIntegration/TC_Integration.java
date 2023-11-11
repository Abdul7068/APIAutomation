package org.example.crudIntegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.example.base.BaseTest;
import org.example.enpoints.APIConstants;
import org.example.payloads.request.Booking;
import org.example.payloads.response.BookingResponse;
import org.testng.annotations.Test;

import java.util.logging.LogManager;

import static org.assertj.core.api.Assertions.assertThat;

public class TC_Integration extends BaseTest {
    String token;
    String bookingidPojo;
    String bookingid;
    private static final LogManager log = LogManager.getLogManager();

    // Create Booking
    @Test(groups = "P0")
    public void testCreateBooking() throws JsonProcessingException {
         token = getToken();
        System.out.println("Token:-" +token);
        assertThat(token).isNotNull().isNotEmpty();
        requestSpecification.basePath(APIConstants.Create_Booking)
                .contentType(ContentType.JSON).log().all();
        Response response = (Response) requestSpecification.when().body(payloadManager.createPayload()).post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        //first way to extract something
        jsonPath = JsonPath.from(response.asString());
        bookingid = jsonPath.getString("bookingid");
        //second way to extract something
        BookingResponse bookingResponse = payloadManager.JsontoObject(response.asString());
        bookingidPojo = bookingResponse.getBookingid().toString();

        System.out.println("json booking id:-" +bookingid);
        System.out.println("Pojo booking id:-" +bookingidPojo);
        assertThat(bookingid).isNotEmpty().isNotNull();



    }

    //Update Created Booking
    @Test(groups = "P0",dependsOnMethods = {"testCreateBooking"})
    public void testUpdateCreateBooking() throws JsonProcessingException {
        System.out.println("json booking id:-" +bookingid);
        System.out.println("Pojo booking id:-" +bookingidPojo);
        System.out.println("Token:-" +token);
        System.out.println("Abdul:-" +bookingid);
        assertThat(token).isNotNull().isNotEmpty();
        requestSpecification.basePath(APIConstants.Update_Booking + "/" +bookingid)
                .contentType(ContentType.JSON).log().all();
        Response response = (Response) requestSpecification.when().cookie("token",token).body(payloadManager.UpdatePayload()).put();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        // first method
        jsonPath = JsonPath.from(response.asString());
        String firstname = jsonPath.getString("Firstname");
        assertThat(firstname).isEqualTo("Lucky");
        //second method
        Booking bookingResponse = payloadManager.JsontoObjectPut(response.asString());
        assertThat(bookingResponse.getFirstname()).isEqualTo("Lucky").isNotNull();
        assertThat(bookingResponse.getLastname()).isEqualTo("Rashid").isNotNull();
    }
    //Delete and verify booking
    @Test(groups = "P0",dependsOnMethods = {"testUpdateCreateBooking"})
    public void testDeleteBooking(){
        requestSpecification.basePath(APIConstants.Update_Booking + "/" +bookingid);
        Response response = (Response) requestSpecification.when().cookie("token",token).delete();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }
    @Test(groups = "P0",dependsOnMethods = {"testDeleteBooking"})
    public void testDeleteBookingByGet() {
        requestSpecification.basePath(APIConstants.Update_Booking + "/" + bookingid);
        Response response = (Response) requestSpecification.when().get();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
    }
}
