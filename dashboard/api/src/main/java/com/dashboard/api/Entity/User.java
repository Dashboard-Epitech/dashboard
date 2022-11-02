package com.dashboard.api.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 256)
  private String lastName;

  @Column(length = 256)
  private String firstName;

  @Column(length = 256, unique = true)
  private String email;

  @Column(length = 256)
  private String password;

  @OneToMany(mappedBy = "User", targetEntity = DashBoard.class, orphanRemoval = true)
  private List<DashBoard> dashboards;

  public User() {
    this.dashboards = new ArrayList<>();
  }

  public Integer getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<DashBoard> getDashboards() {
    return dashboards;
  }

  public void setDashboards(List<DashBoard> dashboards) {
    this.dashboards = dashboards;
  }

  public void addDashBoard(DashBoard dashBoard) {
    this.dashboards.add(dashBoard);
  }

  public void removeDashBoard(DashBoard dashBoard) {
    this.dashboards.remove(dashBoard);
  }
}
