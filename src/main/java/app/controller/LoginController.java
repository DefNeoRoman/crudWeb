package app.controller;


import app.AppManager;
import app.model.User;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
@RequestMapping(value = "/login")
@SessionAttributes(types = User.class)
public class LoginController {


    private UserService userService;

    public LoginController() {
        userService = AppManager.getService();
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView doGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("loginedUser",new User());
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView doPost(@RequestParam String name, @RequestParam String password) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByNameAndPassword(name, password);
        if (user == null) {
            String errorMessage = "Invalid userName or Password";
            modelAndView.addObject("message",errorMessage);
            modelAndView.setViewName("/login");
            return modelAndView;

        }else{
            String message = "login success";
            modelAndView.getModel().put("loginedUser",user);
            modelAndView.addObject("userName", user.getName());
            modelAndView.addObject("message", message);
            modelAndView.setViewName("/login");
            return modelAndView;
        }
    }
}
