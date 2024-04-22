package ru.sklis.spring_boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sklis.spring_boot_security.dao.RoleRepository;
import ru.sklis.spring_boot_security.model.Role;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void save(Role roleName) {
        roleRepository.save(roleName);
    }

}
