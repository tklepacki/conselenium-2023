package part10.posts;

import io.restassured.response.Response;

public interface IPostsService {

    Response getPost(String postId);

    Response getPostList();

    Response addPost(Post post);

    Response editPost(String postId, Post post);

    Response deletePost(String postId);

}