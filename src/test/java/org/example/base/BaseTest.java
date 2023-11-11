package org.example.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.Assertactions;
import org.example.enpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public static RequestSpecification requestSpecification = RestAssured.given();
    public static Assertactions actions;
    public static PayloadManager payloadManager;
    public static JsonPath jsonPath;
    public static Response response;
    public static ValidatableResponse validatableResponse;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        payloadManager = new PayloadManager();
        actions = new Assertactions();
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri(APIConstants.Base_Url)
         .contentType(ContentType.JSON).log().all();

    }

    // Get token
    public String getToken() throws JsonProcessingException {
        String payload = payloadManager.settheToken();
        requestSpecification = RestAssured.given().baseUri(APIConstants.Base_Url).basePath("/auth");
        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload).when().post();
        jsonPath = new JsonPath(response.asString());
        String Token = jsonPath.getString("token");
        return Token;

    }
}
