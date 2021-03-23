package pl.ampv.registration.service.impl;

import org.springframework.stereotype.Service;
import pl.ampv.registration.model.Role;
import pl.ampv.registration.repository.RoleRepository;
import pl.ampv.registration.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
