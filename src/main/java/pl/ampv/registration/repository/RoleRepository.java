package pl.ampv.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ampv.registration.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
