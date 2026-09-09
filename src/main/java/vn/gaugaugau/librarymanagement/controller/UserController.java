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
import vn.gaugaugau.librarymanagement.model.User;
import vn.gaugaugau.librarymanagement.response.ApiResponse;
import vn.gaugaugau.librarymanagement.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/users")
	public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user) {
		User userController = this.userService.handleCreateUser(user);
		return ApiResponse.created(userController);
	}

	@GetMapping("/users")
	public ResponseEntity<ApiResponse<List<User>>> getAllUser() {
		List<User> listUser = this.userService.findAllUser();
		return ApiResponse.success(listUser);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
		User user = this.userService.findUserById(id);
		return ApiResponse.success(user);
	}

}
