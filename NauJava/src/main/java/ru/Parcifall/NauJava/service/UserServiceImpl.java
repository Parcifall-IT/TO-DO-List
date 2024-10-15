package ru.Parcifall.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.Parcifall.NauJava.ent.User;
import ru.Parcifall.NauJava.repo.SubscriptionRepository;
import ru.Parcifall.NauJava.repo.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public UserServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository, PlatformTransactionManager transactionManager) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteBySubscription(String subscription) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<User> users = userRepository.findBySubscription(subscription);
            for (User user : users) {
                userRepository.delete(user.getId());
            }
            transactionManager.commit(status);
        }
        catch (DataAccessException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
