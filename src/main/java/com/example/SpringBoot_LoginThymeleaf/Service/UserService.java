package com.example.SpringBoot_LoginThymeleaf.Service;

import com.example.SpringBoot_LoginThymeleaf.DAO.UserRepository;
import com.example.SpringBoot_LoginThymeleaf.Entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean registerUser(User user, Model model) {
        User status = userRepository.findByEmail(user.getEmail());

        if (status != null) {
            model.addAttribute("registrationError", "User already registered!");
            return false;
        }else{
            model.addAttribute("registrationSuccess", "User registered successfully");
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public boolean loginUser(String email, String password, Model model) {
        User status = userRepository.findByEmail(email);

        if (status != null) {
            if(!password.equals(status.getPassword())){
                model.addAttribute("loginError", "Invalid username or password!");
                return false;
            }
        }else{
            model.addAttribute("loginError", "User not registered!");
            return false;
        }
        model.addAttribute("user_name",status.getName());
        model.addAttribute("user_email",status.getEmail());
        return true;
    }

    public boolean userVerify(User user, Model model) {
        if(!user.getEmail().contains("@gmail.com")){
            model.addAttribute("verificationError", "Invalid email address!");
            return false;
        }else{
            for(char ch='A'; ch<='Z'; ch++){
                if(user.getEmail().contains(String.valueOf(ch))){
                    model.addAttribute("verificationError", "Invalid email address!");
                    return false;
                }
            }
        }

        if(user.getName()!=null){
            String name = user.getName();
            for(int i=0;i<name.length();i++){
                if(!Character.isLetter(name.charAt(i)) && name.charAt(i)!=' '){
                    model.addAttribute("verificationError", "Invalid name!");
                    return false;
                }
            }
        }

        if(user.getPhone()!=null){
            String phone = user.getPhone();
            if(phone.length()!=10) {
                model.addAttribute("verificationError", "Invalid phone number!");
                return false;
            }
            for(int i=0;i<phone.length();i++){
                if(!Character.isDigit(phone.charAt(i))){
                    model.addAttribute("verificationError", "Invalid phone number!");
                    return false;
                }
            }
        }
        return true;
    }
}
