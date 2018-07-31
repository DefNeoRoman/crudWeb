package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import java.io.IOException;
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected String doGet(Model model) throws ServletException, IOException {
        return "/login";
    }
}
