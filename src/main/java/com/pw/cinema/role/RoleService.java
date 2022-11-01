package com.pw.cinema.role;

import com.pw.cinema.user.User;
import com.pw.cinema.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public Role saveRole(Role role) {
        log.info("Save new role {} to database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName){
        log.info("Add new role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
}
