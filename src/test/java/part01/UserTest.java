package part01;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UserTest {

    @Test
    public void getUserTest() {
        given().
                header("x-api-key", "reqres-free-v1").
        when().
                get("https://reqres.in/api/users/2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void getUserListTest() {
        given().
                header("x-api-key", "reqres-free-v1").
        when().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                log().all();
    }
}