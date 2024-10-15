package ru.Parcifall.NauJava.repo;

import org.springframework.data.repository.CrudRepository;
import ru.Parcifall.NauJava.ent.TaskStatus;

public interface TaskStatusRepository extends CrudRepository<TaskStatus, Long> {
}
