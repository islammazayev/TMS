package se.TMS.data_access;

import se.TMS.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("roleDao")
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
