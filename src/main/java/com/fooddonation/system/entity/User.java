package com.fooddonation.system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // ==========================================
    // PRIMARY KEY
    // ==========================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // NAME
    // ==========================================
    @Column(nullable = false, length = 100)
    private String name;

    // ==========================================
    // EMAIL (UNIQUE)
    // ==========================================
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    // ==========================================
    // PASSWORD
    // ==========================================
    @Column(nullable = false, length = 255)
    private String password;

    // ==========================================
    // ROLE
    // ==========================================
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    // ==========================================
    // ACTIVE STATUS
    // ==========================================
    @Column(nullable = false)
    private boolean active = true;

    // ==========================================
    // DEFAULT CONSTRUCTOR
    // ==========================================
    public User() {}

    // ==========================================
    // PARAMETERIZED CONSTRUCTOR
    // ==========================================
    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
    }

    // ==========================================
    // GETTERS & SETTERS
    // ==========================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // ==========================================
    // CLEAN TO STRING (NO PASSWORD LEAK)
    // ==========================================
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", active=" + active +
                '}';
    }
}