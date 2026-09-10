package vn.gaugaugau.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.gaugaugau.librarymanagement.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsByName(String name);
}
