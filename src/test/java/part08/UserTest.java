package part08;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class  UserTest {

    private static RequestSpecification requestSpec;

    static Stream<Arguments> userData() {
        return Stream.of(
                arguments(1, "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg"),
                arguments(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg"),
                arguments(3, "emma.wong@reqres.in", "Emma", "Wong", "https://reqres.in/img/faces/3-image.jpg")
        );
    }

    static Stream<Arguments> userListData() {
        return Stream.of(
                arguments(1, 6, 12, 2, 1, "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg"),
                arguments(2, 6, 12, 2, 7, "michael.lawson@reqres.in", "Michael", "Lawson", "https://reqres.in/img/faces/7-image.jpg")
        );
    }

    @BeforeAll
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api/users").
                addHeader("x-api-key", "reqres-free-v1").
                build();
    }

    @ParameterizedTest
    @MethodSource("userData")
    public void getUserTest(Integer userId, String email, String firstName, String lastName, String avatar) {
        given().
                spec(requestSpec).
                pathParam("userId", userId).

                when().
                get("/{userId}").

                then().
                body(matchesJsonSchemaInClasspath("schemas/user.json")).
                rootPath("data").
                body("id", equalTo(userId)).
                body("email", equalTo(email)).
                body("first_name", equalTo(firstName)).
                body("last_name", equalTo(lastName)).
                body("avatar", equalTo(avatar)).
                contentType("application/json;charset=UTF-8").
                statusCode(200);
    }

    @ParameterizedTest
    @MethodSource("userListData")
    public void getUserListTest(Integer pageId, Integer perPage, Integer total, Integer totalPages, Integer userId, String email, String firstName, String lastName, String avatar) {
        given().
                spec(requestSpec).
                queryParam("page", pageId).

                when().
                get().

                then().
                body(matchesJsonSchemaInClasspath("schemas/userList.json")).
                body("page", equalTo(pageId)).
                body("per_page", equalTo(perPage)).
                body("total", equalTo(total)).
                body("total_pages", equalTo(totalPages)).

                rootPath("data").
                body("id[0]", equalTo(userId)).
                body("email[0]", equalTo(email)).
                body("first_name[0]", equalTo(firstName)).
                body("last_name[0]", equalTo(lastName)).
                body("avatar[0]", equalTo(avatar)).

                contentType("application/json;charset=UTF-8").
                statusCode(200);
    }
}