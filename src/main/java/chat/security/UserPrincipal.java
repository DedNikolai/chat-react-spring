package chat.security;

import chat.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {

  private Long id;
  private String email;
  private String password;
  private Boolean enabled;
  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities, Boolean enabled) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.enabled = enabled;
  }

  public static UserPrincipal create(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
        new SimpleGrantedAuthority(role.name())
    ).collect(Collectors.toList());

    return new UserPrincipal(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        authorities,
//        user.getConfirmed()
        true
    );
  }

  public Long getId() {
    return id;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
