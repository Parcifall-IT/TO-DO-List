package ru.Parcifall.NauJava.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Parcifall.NauJava.ent.NewTask;

import java.util.List;

@RepositoryRestResource
public interface NewTaskRepository extends CrudRepository<NewTask, Long> {
    List<NewTask> findByTitleAndDescription(String title, String description);
}
