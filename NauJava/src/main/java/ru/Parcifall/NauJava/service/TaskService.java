package ru.Parcifall.NauJava.service;

import ru.Parcifall.NauJava.ent.Task;

import java.util.Optional;

public interface TaskService {
    Optional<Task> findById(Long id);
}
