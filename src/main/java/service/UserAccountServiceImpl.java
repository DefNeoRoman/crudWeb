package service;

import model.User;

import javax.servlet.http.HttpSession;

public class UserAccountServiceImpl implements UserAccountService {
    public User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }
}
