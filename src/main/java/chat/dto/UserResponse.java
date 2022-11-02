package chat.dto;

import chat.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
  private Long id;
  private String email;
  private String firstName;
  private String lastName;
  private Set<Role> roles;
}
