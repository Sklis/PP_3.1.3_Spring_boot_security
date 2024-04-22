package ru.sklis.spring_boot_security.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sklis.spring_boot_security.dao.UserRepository;
import ru.sklis.spring_boot_security.model.Role;
import ru.sklis.spring_boot_security.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
//    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository/*, RoleService roleService*/) {
//        this.roleService = roleService;
        this.userRepository = userRepository;
    }
    // так как UserRepository extends JpaRepository, тo Spring все делает сам

    // и анатации @Transactional нам больше не нужны
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findUsersByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User updatedUser) {
        User userToUpdate = userRepository.getById(updatedUser.getId());
        userToUpdate.setUsername(updatedUser.getUsername());
        userToUpdate.setPassword(updatedUser.getPassword());
        userToUpdate.setFirstName(updatedUser.getFirstName());
        userToUpdate.setLastName(updatedUser.getLastName());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setRoles(updatedUser.getRoles());
        userRepository.save(userToUpdate);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User getUser(long id) {
        User user = null;
        Optional<User> tmp = userRepository.findById(id);
        if (tmp.isPresent()) {
            user = tmp.get();
        }
        return user;
    }

    public boolean uniqueUsername(User user) {
        List<User> tmp = getAllUsers();
        return tmp.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        Hibernate.initialize(user.getRoles());
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Пользователь с именем '%s' не найден", username));
        }

        return user;
    }


//    public User getById(long id) {
//        return userRepository.getById(id);
//    }
}