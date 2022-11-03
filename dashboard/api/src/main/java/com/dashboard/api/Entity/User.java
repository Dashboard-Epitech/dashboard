package com.dashboard.api.Entity;

import java.sql.Date;
import java.util.Collection;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;

import com.dashboard.api.Security.Roles.Role;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String username;

  @Email
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<Role> roles = new ArrayList<>();

  @CreatedDate
  private Date dateCreated;

  public User() {
  }

  public User(
    Long id, 
    String username,
    String email,
    Collection<Role> roles,
    Date dateCreated
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.dateCreated = dateCreated;
  }

  public User setId(Long id) {
    this.id = id;
    return this;
  }

  public User setUsername(String name) {
    this.username = name;
    return this;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public User setDateCreted(Date date) {
    this.dateCreated = date;
    return this;
  }

  public Long getId() {
    return this.id;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }

  public Date getDateCreated() {
    return this.dateCreated;
  }
}
