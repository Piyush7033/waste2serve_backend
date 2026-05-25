package com.fooddonation.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "request")
public class Request {

    // =========================================
    // ID
    // =========================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================================
    // FOOD
    // =========================================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    // =========================================
    // RECEIVER
    // =========================================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    // =========================================
    // STATUS
    // =========================================
    @Enumerated(EnumType.STRING)

    @Column(
            name = "status",
            nullable = false,
            length = 20
    )
    private RequestStatus status;

    // =========================================
    // REQUEST TIME
    // =========================================
    @Column(name = "request_time")
    private LocalDateTime requestTime;

    // =========================================
    // MESSAGE
    // =========================================
    @Column(length = 500)
    private String message;
}