import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UpdateUser {

    @Test
    public void updateUserInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", "Sabbir");

        given()
                .pathParam("username", "sabbir")
                .body(jsonObject.toJSONString())
                .contentType("application/json")
                .when()
                .put(BaseClass.baseUrl + "user/{username}")
                .then()
                .log().body()
                .statusCode(200); // Adjust the assertion based on the actual response behavior
    }
}
