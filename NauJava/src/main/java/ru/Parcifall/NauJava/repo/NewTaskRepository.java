package ru.Parcifall.NauJava.repo;

import org.springframework.data.repository.CrudRepository;
import ru.Parcifall.NauJava.ent.NewTask;

import java.util.List;

public interface NewTaskRepository extends CrudRepository<NewTask, Long> {
    List<NewTask> findByTitleAndDescription(String title, String description);
}
