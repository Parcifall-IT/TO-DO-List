package ru.Parcifall.NauJava.repo;

import org.springframework.data.repository.CrudRepository;
import ru.Parcifall.NauJava.ent.TaskType;

public interface TaskTypeRepository extends CrudRepository<TaskType, Long> {
}
