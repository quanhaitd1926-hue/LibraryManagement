package vn.gaugaugau.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.exception.ResourceAlreadyExistsException;
import vn.gaugaugau.librarymanagement.exception.ResourceNotFoundException;
import vn.gaugaugau.librarymanagement.model.Role;
import vn.gaugaugau.librarymanagement.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository roleRepository;

	public Role handleCreateRole(Role role) {
		boolean checkExistsByName = this.roleRepository.existsByName(role.getName().toUpperCase());
		if (checkExistsByName) {
			throw new ResourceAlreadyExistsException("Name already exists.");
		} else {
			role.setName(role.getName().toUpperCase());
			return this.roleRepository.save(role);
		}
	}

	public List<Role> findAllRole() {
		List<Role> listRole = this.roleRepository.findAll();
		return listRole;
	}

	public Role findRoleById(Long id) {
		Optional<Role> roleOpt = this.roleRepository.findById(id);
		if (!roleOpt.isPresent()) {
			throw new ResourceNotFoundException("id not found!");
		} else {
			return roleOpt.get();
		}
	}
}
