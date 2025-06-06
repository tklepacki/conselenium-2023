package part11;

import org.junit.jupiter.api.Test;
import part11.posts.Post;

import static org.hamcrest.Matchers.*;

public class PostTest {

    @Test
    public void addPostTest() {

        Post post = new Post().setTitle("TestTitle").setViews(200);

        String addedPostId = RestService.getPostsService().addPost(post).
                then().
                body("views", equalTo(post.getViews())).
                body("title", equalTo(post.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(post.getViews())).
                body("title", equalTo(post.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        Post post = new Post().setTitle("TestTitle").setViews(200);

        String addedPostId = RestService.getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        Post updatedPost = new Post().setTitle("TestTitleUpdated").setViews(300);

        String updatedPostId = RestService.getPostsService().editPost(addedPostId, updatedPost).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(updatedPost.getViews())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200).
                extract().
                path("id");

        RestService.getPostsService().getPost(updatedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(updatedPost.getViews())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostListTest() {

        Post post = new Post().setTitle("TestTitle").setViews(200);

        String addedPostId = RestService.getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPostList().
                then().
                body("find { it.id == '" + addedPostId + "' }.id", equalTo(addedPostId)).
                body("find { it.id == '" + addedPostId + "' }.title", equalTo(post.getTitle())).
                body("find { it.id == '" + addedPostId + "' }.views", equalTo(post.getViews())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        Post post = new Post().setTitle("TestTitle").setViews(200);

        String addedPostId = RestService.getPostsService().addPost(post).
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

