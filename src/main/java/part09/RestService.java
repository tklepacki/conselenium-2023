package part09;

import part09.users.UsersService;

public class RestService {
    public static UsersService getUsersService() {
        return new UsersService();
    }
}

