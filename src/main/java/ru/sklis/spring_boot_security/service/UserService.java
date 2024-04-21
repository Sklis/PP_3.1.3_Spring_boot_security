package ru.sklis.spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sklis.spring_boot.dao.UserRepository;
import ru.sklis.spring_boot.model.User;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // так как UserRepository extends JpaRepository, тo Spring все делает сам
    // и анатации @Transactional нам больше не нужны

    //    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    //    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    //    @Transactional(readOnly = true)
    public User getUser(long id) {
        User user = null;
        Optional<User> tmp = userRepository.findById(id);
        if (tmp.isPresent()) {
            user = tmp.get();
        }
        return user;
    }
}