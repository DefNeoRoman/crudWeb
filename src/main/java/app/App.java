package app;

import service.UserService;

public class App {
    private static UserService service = new UserService();
    public static void main(String[] args) {
        // запуск через томкат
    }

    public static UserService getUserService(){
        return service;
    }

}
