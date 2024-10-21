package ru.Parcifall.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.Parcifall.NauJava.ent.NewTask;
import ru.Parcifall.NauJava.ent.User;
import ru.Parcifall.NauJava.repo.NewTaskRepository;
import ru.Parcifall.NauJava.repo.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RESTController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewTaskRepository taskRepository;

    @GetMapping("/findBySubscription")
    public List<User> findBySubscription(@RequestParam String subscription) {
        return userRepository.findBySubscription(subscription);
    }

    @GetMapping("/findByTitleAndDescription")
    public List<NewTask> findByTitleAndDescription(@RequestParam String title, @RequestParam String description) {
        return taskRepository.findByTitleAndDescription(title, description);
    }
}
