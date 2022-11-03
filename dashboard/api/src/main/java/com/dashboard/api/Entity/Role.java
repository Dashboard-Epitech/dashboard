package com.dashboard.api.Security.Roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id; 
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }
}
