package part12;

import org.junit.jupiter.api.*;
import part12.posts.Post;

import static org.hamcrest.Matchers.*;

public class PostTest {

    private final Post addPostBody = (Post) FileHelper.generateObjectFromResource("addPostBody.json", Post.class);
    private final Post editPostBody = (Post) FileHelper.generateObjectFromResource("editPostBody.json", Post.class);

    @Test
    public void addPostTest() {

        Post post = new Post().setTitle("TestTitle").setAuthor("TestAuthor");

        Integer addedPostId = RestService.getPostsService().addPost(post).
                then().
                body("author", equalTo(post.getAuthor())).
                body("title", equalTo(post.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(post.getAuthor())).
                body("title", equalTo(post.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        Post post = new Post().setTitle("TestTitle").setAuthor("TestAuthor");

        Integer addedPostId = RestService.getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        Post updatedPost = new Post().setTitle("TestTitleUpdated").setAuthor("TestAuthorUpdated");

        Integer updatedPostId = RestService.getPostsService().editPost(addedPostId, updatedPost).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(updatedPost.getAuthor())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200).
                extract().
                path("id");

        RestService.getPostsService().getPost(updatedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(updatedPost.getAuthor())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostListTest() {

        Post post = new Post().setTitle("TestTitle").setAuthor("TestAuthor");

        Integer addedPostId = RestService.getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPostList().
                then().
                body("find { it.id == " + addedPostId + " }.id", equalTo(addedPostId)).
                body("find { it.id == " + addedPostId + " }.title", equalTo(post.getTitle())).
                body("find { it.id == " + addedPostId + " }.author", equalTo(post.getAuthor())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        Post post = new Post().setTitle("TestTitle").setAuthor("TestAuthor");

        Integer addedPostId = RestService.getPostsService().addPost(post).
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

    @Test
    public void addPostBodyFromFileTest() {
        Integer addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                body("author", equalTo("TestAuthor")).
                body("title", equalTo("TestTitle")).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo("TestAuthor")).
                body("title", equalTo("TestTitle")).
                statusCode(200);
    }

    @Test
    public void editPostBodyFromFileTest() {

        Integer addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        Integer updatedPostId = RestService.getPostsService().editPost(addedPostId, editPostBody).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo("TestAuthorUpdated")).
                body("title", equalTo("TestTitleUpdated")).
                statusCode(200).
                extract().
                path("id");

        RestService.getPostsService().getPost(updatedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo("TestAuthorUpdated")).
                body("title", equalTo("TestTitleUpdated")).
                statusCode(200);
    }
}