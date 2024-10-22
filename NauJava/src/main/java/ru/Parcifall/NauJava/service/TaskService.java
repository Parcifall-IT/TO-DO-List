package ru.Parcifall.NauJava.service;

import ru.Parcifall.NauJava.ent.Task;
import ru.Parcifall.NauJava.ent.TaskStatus;
import ru.Parcifall.NauJava.ent.TaskType;

import java.util.Calendar;

public interface TaskService {
    void createTask(TaskType type, String name, String description, Calendar deadline, TaskStatus status);

    Task findById(Long id);

    void deleteById(Long id);

    void updateStatus(Long id, TaskStatus status);

    void updateDeadline(Long id, Calendar deadline);
}
