package ru.Parcifall.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Parcifall.NauJava.ent.Task;
import ru.Parcifall.NauJava.ent.User;
import ru.Parcifall.NauJava.repo.TaskRepository;
import ru.Parcifall.NauJava.service.TaskService;
import ru.Parcifall.NauJava.service.UserService;

import java.lang.Exception;
import java.util.Map;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
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

    @GetMapping("/admin-dashboard")
    public String admin() {
        return "admin-dashboard";
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

    @GetMapping("/edit-profile")
    public String editProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByName(userDetails.getUsername());
        if (user != null) {
            model.addAttribute("user", user);

            List<Task> tasks = user.getTasks();
            int taskCounter = tasks.size();
            int finishedTaskCounter = tasks.stream().filter(Task::isCompleted).toList().size();
            model.addAttribute("taskCounter", taskCounter);
            model.addAttribute("finishedTaskCounter", finishedTaskCounter);
        }
        return "profile";
    }

    @PostMapping("/update-task")
    public String updateTask(@ModelAttribute Task task, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByName(userDetails.getUsername());
        if (user != null) {
            Task existingTask = taskService.findById(task.getId()).orElse(null);
            if (existingTask != null && user.getTasks().contains(existingTask)) {
                existingTask.setTitle(task.getTitle());
                existingTask.setDescription(task.getDescription());
                existingTask.setDeadline(task.getDeadline());
                taskService.updateTask(existingTask);
            }
        }
        return "redirect:/home";
    }

    @GetMapping("/edit-task")
    public ResponseEntity<Task> getTask(@RequestParam Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByName(userDetails.getUsername());
        if (user != null) {
            Task task = taskService.findById(id).orElse(null);
            if (task != null && user.getTasks().contains(task)) {
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
