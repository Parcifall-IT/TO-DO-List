package ru.Parcifall.NauJava.crit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.Parcifall.NauJava.ent.Subscription;
import ru.Parcifall.NauJava.ent.User;
import ru.Parcifall.NauJava.repo.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findBySubscription(String subscription) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);
        Join<User, Subscription> sub = userRoot.join("subscription", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(sub.get("title"), subscription);

        criteriaQuery.select(userRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
