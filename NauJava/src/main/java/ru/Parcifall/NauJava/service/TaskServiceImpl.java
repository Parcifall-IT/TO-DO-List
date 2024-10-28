package ru.Parcifall.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.Parcifall.NauJava.ent.Task;
import ru.Parcifall.NauJava.ent.TaskStatus;
import ru.Parcifall.NauJava.ent.TaskType;
import ru.Parcifall.NauJava.repo.TaskRepository;

import java.util.Calendar;
import java.util.Optional;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }
}
