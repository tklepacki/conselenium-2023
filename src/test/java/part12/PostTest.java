package part12;

import org.junit.jupiter.api.*;
import part12.posts.Post;

import static org.hamcrest.Matchers.*;

public class PostTest {

    private final Post addPostBody = (Post) FileHelper.generateObjectFromResource("addPostBody.json", Post.class);
    private final Post editPostBody = (Post) FileHelper.generateObjectFromResource("editPostBody.json", Post.class);

    @Test
    public void addPostTest() {
        Integer addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                body("author", equalTo(addPostBody.getAuthor())).
                body("title", equalTo(addPostBody.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(addPostBody.getAuthor())).
                body("title", equalTo(addPostBody.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        Integer addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        Integer updatedPostId = RestService.getPostsService().editPost(addedPostId, editPostBody).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(editPostBody.getAuthor())).
                body("title", equalTo(editPostBody.getTitle())).
                statusCode(200).
                extract().
                path("id");

        RestService.getPostsService().getPost(updatedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(editPostBody.getAuthor())).
                body("title", equalTo(editPostBody.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostTest() {
        Integer addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPostList().
                then().
                body("find { it.id == " + addedPostId + " }.id", equalTo(addedPostId)).
                body("find { it.id == " + addedPostId + " }.title", equalTo(addPostBody.getTitle())).
                body("find { it.id == " + addedPostId + " }.author", equalTo(addPostBody.getAuthor())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        Integer addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().deletePost(addedPostId).
                then().
                statusCode(200);

        RestService.getPostsService().getPostList().
                then().
                body("id", not(hasItems(addedPostId)));
    }
}