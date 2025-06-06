package part04;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTest {

    private static RequestSpecification requestSpec;

    @BeforeAll
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api/users").
                addHeader("x-api-key", "reqres-free-v1").
                build();
    }

    @Test
    public void getUserTest() {
        given().
                spec(requestSpec).
                pathParam("userId", 2).

                when().
                get("/{userId}").

                then().
                body(matchesJsonSchemaInClasspath("schemas/user.json")).
                statusCode(200).
                log().all();
    }

    @Test
    public void getUserListTest() {
        given().
                spec(requestSpec).
                queryParam("page", "2").

                when().
                get().

                then().
                body(matchesJsonSchemaInClasspath("schemas/userList.json")).
                statusCode(200).
                log().all();
    }
}