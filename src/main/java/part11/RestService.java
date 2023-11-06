package part11;

import part11.posts.PostsService;
import part11.users.UsersService;

public class RestService {

    public static UsersService getUsersService() {
        return new UsersService();
    }

    public static PostsService getPostsService() {
        return new PostsService();
    }
}
