package com.fooddonation.system.service;

import com.fooddonation.system.dto.FoodRequestDto;
import com.fooddonation.system.entity.Food;

import java.util.List;

public interface FoodService {

    // ================= AVAILABLE FOODS (RECEIVER) =================
    List<Food> getAvailableFoods();

    // ================= ADD FOOD (DONOR) =================
    Food addFood(FoodRequestDto dto, String email);

    // ================= GET MY FOODS (DONOR) =================
    List<Food> getMyFood(String email);

    // ================= UPDATE FOOD (DONOR OWNER ONLY) =================
    Food updateFood(Long id, FoodRequestDto dto, String email);

    // ================= DELETE FOOD (DONOR OWNER ONLY) =================
    void deleteFood(Long id, String email);
}