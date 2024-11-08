package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import login.service.UserValidationService;

@Controller
public class LoginController {

    @Autowired
    UserValidationService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String handleLoginRequest(@RequestParam String name, @RequestParam String password, ModelMap model) {
        if (!service.validateUser(name, password)){
            model.put("error", "Invalid username or password");
            return "login with correct credentials";
        }

        else {
            model.put("name", name);
            model.put("password", password);
            return "welcome";
        }
    }
}
