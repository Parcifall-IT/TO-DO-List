package ru.Parcifall.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.Parcifall.NauJava.ent.User;
import ru.Parcifall.NauJava.repo.UserRepository;

@Controller
@RequestMapping("/custom/users/view")
public class UserControllerView {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String userListView(Model model) {
        Iterable<User> products = userRepository.findAll();
        model.addAttribute("users", products);
        return "userList";
    }
}
