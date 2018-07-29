package app;

import app.service.UserService;
import app.service.UserServiceImpl;

public class AppManager {
    private static UserService service = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        // запуск через томкат
    }

    public static UserService getService() {
        return service;
    }


}
