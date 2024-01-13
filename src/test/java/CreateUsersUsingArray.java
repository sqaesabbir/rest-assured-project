import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CreateUsersUsingArray {


    @Test
    public void createUserList() {
        // Create user 1
        JSONObject user1 = createUserObject("1", "sabbir1", "Md. Sabbir", "Hossain", "sabbir1@gmail.com", "s@bbir1", "6563232", 1);

        // Create user 2
        JSONObject user2 = createUserObject("2", "sabbir2", "Md. Sabbir", "Hossain", "sabbir2@gmail.com", "s@bbir2", "6563233", 1);

        // Create user 3
        JSONObject user3 = createUserObject("2", "sabbir2", "Md. Sabbir", "Hossain", "sabbir2@gmail.com", "s@bbir2", "6563233", 1);

        // Create a JSON array with user objects
        JSONArray userArray = new JSONArray();
        userArray.add(user1);
        userArray.add(user2);
        userArray.add(user3);

        // Send POST request to create the list of user
        Response responseData = given()
                .body(userArray.toJSONString())  // Directly use toJSONString() on the JSONArray
                .contentType("application/json")
                .when()
                .post(BaseClass.baseUrl + "user/createWithArray")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        JsonPath jsonPathEvaluator = responseData.jsonPath();
        String message = jsonPathEvaluator.get("message");
        System.out.println("API Message: " + message);
    }


    private JSONObject createUserObject(String id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        JSONObject user = new JSONObject();
        user.put("id", id);
        user.put("username", username);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        user.put("password", password);
        user.put("phone", phone);
        user.put("userStatus", userStatus);
        return user;
    }
}
