package com.fooddonation.system.dto;

import com.fooddonation.system.entity.FoodStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class FoodResponseDto {

    private String title;
    private String quantity;
    private String location;
    private LocalDateTime expiryTime;
    private FoodStatus status;
    private String donorName;

    public FoodResponseDto(Long id, String title, String description, String quantity,
                           String location, LocalDateTime expiryTime,
                           FoodStatus status, String donorName) {
        this.title = title;
        this.quantity = quantity;
        this.location = location;
        this.expiryTime = expiryTime;
        this.status = status;
        this.donorName = donorName;
    }

    // getters
}