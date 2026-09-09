package vn.gaugaugau.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.exception.ResourceAlreadyExistsException;
import vn.gaugaugau.librarymanagement.exception.ResourceNotFoundException;
import vn.gaugaugau.librarymanagement.model.User;
import vn.gaugaugau.librarymanagement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User handleCreateUser(User user) {
		boolean checkExistsByEmail = this.userRepository.existsByEmail(user.getEmail());
		if (checkExistsByEmail) {
			throw new ResourceAlreadyExistsException("Email already exists!");
		} else {
			String hashPassword = this.passwordEncoder.encode(user.getPassword());
			user.setPassword(hashPassword);
			return this.userRepository.save(user);
		}
	}

	public List<User> findAllUser() {
		return this.userRepository.findAll();
	}

	public User findUserById(Long id) {
		Optional<User> userOpt = this.userRepository.findById(id);
		if (!userOpt.isPresent()) {
			throw new ResourceNotFoundException("id not exists !");
		} else {
			return userOpt.get();
		}
	}
}
