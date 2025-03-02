package com.example.SpringBoot_LoginThymeleaf.Service;

import com.example.SpringBoot_LoginThymeleaf.Entites.User;
import org.springframework.ui.Model;

public interface UserServices {

    boolean registerUser(User user, Model model);

    boolean loginUser(String email, String password, Model model);
}
