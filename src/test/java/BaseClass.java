import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class BaseClass {

    public static String baseUrl = "https://petstore.swagger.io/v2/";

    @Test
    public void createUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", "1");
        requestBody.put("username", "sabbir");
        requestBody.put("firstName", "Md. Sabbir");
        requestBody.put("lastName", "Hossain");
        requestBody.put("email", "sabbir@gmail.com");
        requestBody.put("password", "s@bbir");
        requestBody.put("phone", "6563232");
        requestBody.put("userStatus", 1);

        Response responseData = given()
                .body(requestBody.toJSONString())
                .contentType("application/json")
                .when()
                .post(baseUrl + "user")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        JsonPath jsonPathEvaluator = responseData.jsonPath();
        int actualStatusCode = jsonPathEvaluator.get("code");
        System.out.println("API Status Code: " + actualStatusCode);
        assertEquals(actualStatusCode, 200);
    }
}
