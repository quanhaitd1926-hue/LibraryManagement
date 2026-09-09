package vn.gaugaugau.librarymanagement.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import vn.gaugaugau.librarymanagement.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllException(Exception e) {
		return ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleNotFound(EntityNotFoundException e) {
		return ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	@ExceptionHandler({ ResourceNotFoundException.class, ResourceAlreadyExistsException.class })
	public ResponseEntity<?> handleNotFoundOrAlreadyExists(Exception e) {
		return ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	// todo: xử lý invalid input
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String errorMessage = String.format("Tham số '%s' có giá trị '%s' không đúng định dạng.", ex.getName(),
				ex.getValue());
		return ApiResponse.error(HttpStatus.BAD_REQUEST, errorMessage);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
		String errors = String.join("; ", errorList);

		ApiResponse<Object> response = new ApiResponse<>(HttpStatus.BAD_REQUEST, errors, null, "VALIDATION_ERROR");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
