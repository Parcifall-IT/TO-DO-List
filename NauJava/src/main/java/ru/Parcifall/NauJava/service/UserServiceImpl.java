package ru.Parcifall.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.Parcifall.NauJava.ent.Role;
import ru.Parcifall.NauJava.ent.User;
import ru.Parcifall.NauJava.repo.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PlatformTransactionManager transactionManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void addUser(User user) throws Exception {
        User userFromDb = userRepository.findByName(user.getName());
        if (userFromDb != null) {
            throw new Exception("user exists");
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteBySubscription(String subscription) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<User> users = userRepository.findBySubscription(subscription);
            for (User user : users) {
                userRepository.delete(user);
            }
            transactionManager.commit(status);
        }
        catch (DataAccessException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userRepository.findByName(username);
        if (myUser != null) {
            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                    myUser.getName(),
                    myUser.getPassword(),
                    mapRoles(myUser));
            return user;
        }
        throw new UsernameNotFoundException("user not found");
    }

    private Collection<GrantedAuthority> mapRoles(User appUser)
    {
        return appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" +
                        role.name())).collect(Collectors.toList());
    }
}
