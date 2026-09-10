package vn.gaugaugau.librarymanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.model.Role;
import vn.gaugaugau.librarymanagement.response.ApiResponse;
import vn.gaugaugau.librarymanagement.service.RoleService;

@RestController
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@PostMapping("/roles")
	public ResponseEntity<ApiResponse<Role>> createRole(@Valid @RequestBody Role role) {
		Role newRole = this.roleService.handleCreateRole(role);
		return ApiResponse.created(newRole);
	}

	@GetMapping("/roles")
	public ResponseEntity<ApiResponse<List<Role>>> getAllRole() {
		List<Role> listRole = this.roleService.findAllRole();
		return ApiResponse.success(listRole);
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<ApiResponse<Role>> getRoleById(@PathVariable Long id) {
		Role role = this.roleService.findRoleById(id);
		return ApiResponse.success(role);
	}
}
