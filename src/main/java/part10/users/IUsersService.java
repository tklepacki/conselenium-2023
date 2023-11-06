package part10.users;

import io.restassured.response.Response;

public interface IUsersService {

    Response getUser(Integer userId);

    Response getUserList(Integer pageId);

}