import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUser {
    @Test
    public void getUser() {
        Response responseData = given()
                .pathParam("username", "sabbir")
                .when()
                .get(BaseClass.baseUrl + "user/{username}")
                .then()
                .assertThat()
                .extract().response();

        JsonPath jsonPathEvaluator = responseData.jsonPath();
        String username = jsonPathEvaluator.get("username");
        System.out.println(username+ "sabbir");

    }
}
