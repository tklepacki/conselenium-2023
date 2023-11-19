import posts.PostsService;
import users.UsersService;

public class RestService {

    public static UsersService getUsersService() {
        return new UsersService();
    }

    public static PostsService getPostsService() {
        return new PostsService();
    }
}


