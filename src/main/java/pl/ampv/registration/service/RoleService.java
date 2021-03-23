package pl.ampv.registration.service;


import pl.ampv.registration.model.Role;

public interface RoleService {

    Role findByName(String name);

}
