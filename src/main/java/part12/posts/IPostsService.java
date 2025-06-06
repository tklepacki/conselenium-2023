package part12.posts;

import io.restassured.response.Response;

public interface IPostsService {

    Response getPost(Integer postId);

    Response getPostList();

    Response addPost(Post post);

    Response editPost(Integer postId, Post post);

    Response deletePost(Integer postId);

}