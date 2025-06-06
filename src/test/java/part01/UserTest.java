package part01;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class UserTest {

    @Test
    public void getUserTest() {
        when().
                get("https://reqres.in/api/users/2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void getUserListTest() {
        when().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                log().all();
    }
}