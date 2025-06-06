package part11.users;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UsersService implements IUsersService {

    private static final String USERS_ENDPOINT = "https://reqres.in/api/users";
    private static final String API_KEY = "reqres-free-v1";

    RequestSpecification requestSpec = new RequestSpecBuilder().
            setBaseUri(USERS_ENDPOINT).
            addHeader("x-api-key", API_KEY).
            build();

    public UsersService() {
        super();
    }

    public Response getUser(Integer userId) {
        Response response = given().
                spec(requestSpec).
                pathParam("userId", userId).
                when().
                get("{userId}");
        return response;
    }

    @Override
    public Response getUserList(Integer pageId) {
        Response response = given().
                spec(requestSpec).
                queryParam("page", pageId).
                when().
                get();
        return response;
    }

}