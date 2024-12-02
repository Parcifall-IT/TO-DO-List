package ru.Parcifall.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Parcifall.NauJava.ent.Task;
import ru.Parcifall.NauJava.ent.User;
import ru.Parcifall.NauJava.repo.TaskRepository;
import ru.Parcifall.NauJava.service.UserService;

import java.lang.Exception;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        try {
            userService.addUser(user);
            return "redirect:/login";
        }
        catch (Exception ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByName(userDetails.getUsername());
        if (user != null) {
            model.addAttribute("tasks", user.getTasks());
        }
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/add-task")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/add-task")
    public String saveTask(@ModelAttribute Task task, @AuthenticationPrincipal UserDetails userDetails) {
        taskRepository.save(task);
        User user = userService.getUserByName(userDetails.getUsername());
        if (user != null) {
            userService.addTaskToUser(user, task);
        }
        return "redirect:/home";
    }
}
