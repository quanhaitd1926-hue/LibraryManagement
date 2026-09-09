package vn.gaugaugau.librarymanagement.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

	private String status;
	private String message;
	private T data;
	private String errorCode;
	private LocalDateTime timestamp = LocalDateTime.now();

	public ApiResponse(HttpStatus httpStatus, String message, T data, String errorCode) {
		this.status = httpStatus.is2xxSuccessful() ? "success" : "error";
		this.message = message;
		this.data = data;
		this.errorCode = errorCode;
		this.timestamp = LocalDateTime.now();
	}

	// 1. Success: Trả về HTTP 200 OK + Data
	public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
		ApiResponse<T> response = new ApiResponse<>(HttpStatus.OK, "Call API success.", data, null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// 2. Success: Trả về HTTP 200 OK + Data + Message tùy chỉnh
	public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
		ApiResponse<T> response = new ApiResponse<>(HttpStatus.OK, message, data, null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// 3. Created: Trả về HTTP 201 Created (Dùng cho tạo mới)
	public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
		ApiResponse<T> response = new ApiResponse<>(HttpStatus.CREATED, "Created successfully", data, null);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// 4. Error: Trả về HTTP Status tùy chỉnh (400, 404, 500...) + Error Code
	public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message, String errorCode) {
		ApiResponse<T> response = new ApiResponse<>(status, message, null, errorCode);
		return ResponseEntity.status(status).body(response);
	}

	// 5. Error cơ bản: Chỉ cần Status và Message
	public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message) {
		return error(status, message, String.valueOf(status.value()));
	}

}
