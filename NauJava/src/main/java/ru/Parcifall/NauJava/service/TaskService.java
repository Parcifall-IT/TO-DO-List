package ru.Parcifall.NauJava.service;

import ru.Parcifall.NauJava.ent.Status;
import ru.Parcifall.NauJava.ent.Task;

import java.util.Calendar;

public interface TaskService {
    void createTask(Long id, String name, String description, Calendar deadline, Status status);

    Task findById(Long id);

    void deleteById(Long id);

    void updateStatus(Long id, Status status);

    void updateDeadline(Long id, Calendar deadline);
}
