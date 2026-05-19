package com.fooddonation.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // FOOD DETAILS
    // =========================
    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String status;

    // =========================
    // 🔥 IMPORTANT FIX (USER LINK)
    // =========================
    @Column(nullable = false)
    private String donorEmail;

    // =========================
    // TIME STAMP
    // =========================
    @Column(name = "donated_at")
    private LocalDateTime donatedAt;

    // =========================
    // AUTO TIME SET
    // =========================
    @PrePersist
    public void prePersist() {
        donatedAt = LocalDateTime.now();
    }
}