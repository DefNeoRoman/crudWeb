package service;

import model.User;

import javax.servlet.http.HttpSession;

public interface UserAccountService {
    User getLoginedUser(HttpSession session);
}
