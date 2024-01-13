import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class CreateUsersUsingList {



    @Test
    public void createUserList() {
        List<JSONObject> userList = new ArrayList<>();

        // Create user 1
        JSONObject user1 = createUserObject("1", "sabbir", "Md. Sabbir", "Hossain", "sabbir1@gmail.com", "s@bbir1", "6563232", 1);
        userList.add(user1);

        // Create user 2
        JSONObject user2 = createUserObject("2", "rakib", "Md. Rakib", "Hasan", "rakib@gmail.com", "s@rakib", "454542425", 1);
        userList.add(user2);

        // Create user 3
        JSONObject user3 = createUserObject("3", "rasel", "Md. Rasel", "Hasan", "rasel@gmail.com", "s@reasel", "4545442425", 1);
        userList.add(user3);

        // Convert the list to a JSONArray
        JSONArray userArray = new JSONArray();
        userArray.addAll(userList);

        // Send POST request to create the list of users
        Response responseData = given()
                .body(userArray.toJSONString())  // Use toJSONString() on the JSONArray, not the List
                .contentType("application/json")
                .when()
                .post(BaseClass.baseUrl + "user/createWithList")
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
