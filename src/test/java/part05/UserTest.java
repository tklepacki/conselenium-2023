package part05;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

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
                body("data.id", equalTo(2)).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver")).
                body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg")).
                contentType("application/json;charset=UTF-8").
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
                body("page", equalTo(2)).
                body("per_page", equalTo(6)).
                body("total", equalTo(12)).
                body("total_pages", equalTo(2)).

                body("data.id[0]", equalTo(7)).
                body("data.email[0]", equalTo("michael.lawson@reqres.in")).
                body("data.first_name[0]", equalTo("Michael")).
                body("data.last_name[0]", equalTo("Lawson")).
                body("data.avatar[0]", equalTo("https://reqres.in/img/faces/7-image.jpg")).

                body("data.id", hasItems(7, 8, 9, 10, 11, 12)).
                body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in")).
                body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel")).
                body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell")).

                contentType("application/json;charset=UTF-8").
                statusCode(200).
                log().all();
    }
}