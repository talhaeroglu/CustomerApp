package com.example.customerapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
public class UserContoller {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);

        return "users";


    }
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        return "user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);
        return "redirect:/users";
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
        }catch (UserPrincipalNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }

}
