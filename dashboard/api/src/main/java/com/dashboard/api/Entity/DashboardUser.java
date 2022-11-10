package com.dashboard.api.Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardUser implements UserDetails {

  @Column(name = "user_id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  @NotBlank(message = "No username found")
  @NotNull(message = "No username found")
  private String username;

  @Column
  @NotBlank(message = "No password found")
  @NotNull(message = "No password found")
  private String password;

  @Column(unique = true)
  @NotBlank(message = "No email found")
  @NotNull(message = "No email found")
  @Email(message = "Invalid email address")
  private String email;

  @ManyToMany
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Collection<Role> roles;

  @Column
  private boolean verified = false;

  @Column
  @Nullable
  private String verificationCode;

  @Column
  @CreationTimestamp
  private LocalDate dateCreated;

  @Column
  @UpdateTimestamp
  private LocalDate dateUpdated;

  @Override
  public boolean isEnabled() {
    return this.verified;
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
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.EMPTY_LIST;
  }
}
