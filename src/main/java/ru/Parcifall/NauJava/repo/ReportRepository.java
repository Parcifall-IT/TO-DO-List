package ru.Parcifall.NauJava.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Parcifall.NauJava.ent.Report;

@RepositoryRestResource
public interface ReportRepository extends CrudRepository<Report, Long> {
}
