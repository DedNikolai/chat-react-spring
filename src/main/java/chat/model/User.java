package chat.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Set<Role> roles = new HashSet<>();

//  @Column(columnDefinition = "boolean default false")
//  private Boolean confirmed = false;

//  @OneToMany(mappedBy = "user")
//  @ToString.Exclude
//  @EqualsAndHashCode.Exclude
//  private Set<Message> messages;

//  @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
//  @ToString.Exclude
//  @EqualsAndHashCode.Exclude
//  private Set<Chat> chats;

}
