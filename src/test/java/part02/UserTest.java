package part02;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTest {

    @Test
    public void getUserTest() {
        given().
                header("x-api-key", "reqres-free-v1").
        when().
                get("https://reqres.in/api/users/2").
                then().
                body(matchesJsonSchemaInClasspath("schemas/user.json")).
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
                body(matchesJsonSchemaInClasspath("schemas/userList.json")).
                statusCode(200).
                log().all();
    }

}