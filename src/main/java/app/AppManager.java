package app;

import service.UserAccountService;
import service.UserService;
import service.UserServiceImpl;

public class AppManager {
    private static UserService service = UserServiceImpl.getInstance();
    private static UserAccountService accountService =  UserAccountService.getINSTANCE();
    public static void main(String[] args) {
        // запуск через томкат
    }

    public static UserService getService() {
        return service;
    }

    public static UserAccountService getAccountService() {
        return accountService;
    }
}
