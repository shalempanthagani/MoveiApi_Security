package com.antra.movieapi;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // BCrypt hashed

    private String role = "ROLE_USER";

    public AppUser() {}

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId()                 { return id; }
    public String getUsername()         { return username; }
    public void setUsername(String u)   { this.username = u; }
    public String getPassword()         { return password; }
    public void setPassword(String p)   { this.password = p; }
    public String getRole()             { return role; }
    public void setRole(String r)       { this.role = r; }
}
