package ru.Parcifall.NauJava.service;

import ru.Parcifall.NauJava.ent.Task;
import ru.Parcifall.NauJava.ent.TaskStatus;
import ru.Parcifall.NauJava.ent.TaskType;

import java.util.Calendar;
import java.util.Optional;

public interface TaskService {
    Optional<Task> findById(Long id);
}
