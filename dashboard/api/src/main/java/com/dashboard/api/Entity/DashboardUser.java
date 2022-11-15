package com.dashboard.api.Entity;

import java.security.AuthProvider;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dashboard.api.Model.Enum.AuthProviderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardUser {

  @Column(name = "user_id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  @NotBlank(message = "No email found")
  @NotNull(message = "No email found")
  @Email(message = "Invalid email address")
  private String email;

  @Column(unique = true)
  @NotBlank(message = "No username found")
  @NotNull(message = "No username found")
  private String username;

  @Column
  @JsonIgnore
  private String password;

  @Column(nullable = false)
  private boolean isVerified = false;

  @Column
  @Nullable
  private String verificationCode;

  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthProviderEnum provider;

  @Column
  @CreationTimestamp
  private LocalDate dateCreated;

  @Column
  @UpdateTimestamp
  private LocalDate dateUpdated;

  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
  @JsonIgnore
  private List<DashBoard> dashBoards = new ArrayList<>();

  @OneToOne
  @JoinColumn(name = "spotify_token_id")
  private SpotifyToken spotifyToken;
}
