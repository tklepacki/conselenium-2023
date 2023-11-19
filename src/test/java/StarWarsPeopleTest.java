import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class StarWarsPeopleTest {

    @Test
    public void getStarWarsPeopleList() {
        when().
                get("https://swapi.dev/api/people").

                then().
                body("results.findAll { (it.height.toInteger()) > 180 }.name", hasItems("Darth Vader", "Biggs Darklighter", "Obi-Wan Kenobi")).
                body("results.findAll { it.gender == 'female' }.size()", equalTo(2)).
                statusCode(200).
                log().all();
    }
}