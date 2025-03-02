package com.example.SpringBoot_LoginThymeleaf.Controller;

import com.example.SpringBoot_LoginThymeleaf.Entites.User;
import com.example.SpringBoot_LoginThymeleaf.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String openRegisterPage(Model model) {
        model.addAttribute("regUser", new User());
        return "register";
    }

    @GetMapping("/login")
    public String openLoginPage(Model model) {
        model.addAttribute("logUser", new User());
        return "login";
    }

    @GetMapping("/register")
    public String openRegPage(Model model) {
        model.addAttribute("regUser", new User());
        return "register";
    }

    @PostMapping("/SubmitReg")
    public String processSignUp(Model model, @ModelAttribute("regUser") User user) {
        boolean status;

        status = userService.userVerify(user, model);
        if(status) userService.registerUser(user, model);


        return "register";
    }

    @PostMapping("/SubmitLog")
    public String processLogin(Model model, @ModelAttribute("logUser") User user) {
        boolean status1;
        boolean status2;

        status1 = userService.userVerify(user, model);
        status2 = userService.loginUser(user.getEmail(), user.getPassword(), model);

        if(status1 && status2) return "profile";

        return "login";
    }

    @GetMapping("/logout")
    public String processLogout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "login";
    }
}
