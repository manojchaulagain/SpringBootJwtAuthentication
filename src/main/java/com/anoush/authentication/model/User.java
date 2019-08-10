package com.anoush.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
  @Id private String id;

  private String firstName;

  private String lastName;

  private String middleName;

  private String username;

  private String email;

  private int age;

  private Set<GroupChat> groupChats = new HashSet<>();

  private Date joinDate;

  private UserStatus userStatus;

  private String deviceId;

  private Location currentLocation;

  private Location lastActiveLocation;

  private Date lastActiveDate;

  private List<User> chatFriendship = new ArrayList<>();

  private String avatarUrl;

  private UserPreference userPreference;

  @JsonIgnore private String password;

  private Set<Role> roles = new HashSet<>();

  public User(String firstName, String lastname, String username, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastname;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public User(
      String firstName,
      String lastname,
      String username,
      String email,
      String password,
      Set<Role> roles) {
    this.firstName = firstName;
    this.lastName = lastname;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }
}
