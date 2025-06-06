package part12.posts;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PostsService implements IPostsService {

    private static final String USERS_ENDPOINT = "http://localhost:3000/posts";

    RequestSpecification requestSpec = new RequestSpecBuilder().
            setBaseUri(USERS_ENDPOINT).
            setContentType(ContentType.JSON).
            build();

    public Response getPost(String postId) {
        Response response = given().
                spec(requestSpec).
                pathParam("postId", postId).
                when().
                get("{postId}");
        return response;
    }

    public Response getPostList() {
        Response response = given().
                spec(requestSpec).
                when().
                get();
        return response;
    }

    public Response addPost(Post post) {
        Response response = given().
                spec(requestSpec).
                body(post).
                when().
                post();
        return response;
    }

    public Response editPost(String postId, Post post) {
        Response response = given().
                spec(requestSpec).
                pathParam("postId", postId).
                body(post).
                when().
                put("{postId}");
        return response;
    }

    public Response deletePost(String postId) {
        Response response = given().
                spec(requestSpec).
                pathParam("postId", postId).
                when().
                delete("{postId}");
        return response;
    }

}