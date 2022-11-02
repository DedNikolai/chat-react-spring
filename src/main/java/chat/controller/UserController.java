package chat.controller;


import chat.dto.LoginRequest;
import chat.dto.UserResponse;
import chat.model.User;
import chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("auth/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(userService.authenticateUser(loginRequest));
  }


  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  @GetMapping("users/current")
  public ResponseEntity<UserResponse> getCurrentUser() {
    ResponseEntity<UserResponse> response = ResponseEntity.ok(userService.getCurrentUser());
    return response;
  }

}
