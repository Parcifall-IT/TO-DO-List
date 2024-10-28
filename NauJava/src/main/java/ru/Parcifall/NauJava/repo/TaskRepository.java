package ru.Parcifall.NauJava.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Parcifall.NauJava.ent.Task;

import java.util.List;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByTitleAndDescription(String title, String description);
}
