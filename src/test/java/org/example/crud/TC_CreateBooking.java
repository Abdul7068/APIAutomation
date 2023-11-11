package org.example.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.Assertactions;
import org.example.base.BaseTest;
import org.example.enpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.example.payloads.request.Booking;
import org.example.payloads.response.BookingResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
public class TC_CreateBooking extends BaseTest {

    @Test(groups = {"stage","P0"})
    @Owner("Abdul")
    @Description("create booking and verify it")
    @Severity(SeverityLevel.CRITICAL)

    public void testCreateBooking() throws JsonProcessingException {
        payloadManager = new PayloadManager();
        actions = new Assertactions();
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri(APIConstants.Base_Url)
                .contentType(ContentType.JSON).log().all();
        requestSpecification.basePath(APIConstants.Create_Booking);
        Response response = (Response) requestSpecification.when().body(payloadManager.createPayload()).post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse = payloadManager.JsontoObject(response.asString());
        assertThat(bookingResponse.getBookingid().toString()).isNotEmpty().isNotNull();
    }
}
