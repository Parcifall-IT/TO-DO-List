package ru.Parcifall.NauJava.service;

import ru.Parcifall.NauJava.data.access.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.Parcifall.NauJava.ent.Task;
import ru.Parcifall.NauJava.ent.TaskStatus;
import ru.Parcifall.NauJava.ent.TaskType;

import java.util.Calendar;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(TaskType type, String name, String description, Calendar deadline, TaskStatus status) {
        Task newTask = new Task(type, name, description, status, deadline);
        taskRepository.create(newTask);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public void updateStatus(Long id, TaskStatus status) {
        Task task = findById(id);
        task.setStatus(status);
        taskRepository.update(task);
    }

    @Override
    public void updateDeadline(Long id, Calendar deadline) {
        Task task = findById(id);
        task.setPeriod(deadline);
        taskRepository.update(task);
    }
}
