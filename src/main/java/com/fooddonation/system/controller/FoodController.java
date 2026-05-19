package com.fooddonation.system.controller;

import com.fooddonation.system.dto.FoodRequestDto;
import com.fooddonation.system.entity.Food;
import com.fooddonation.system.service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "http://localhost:5173")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public ResponseEntity<?> addFood(@RequestBody FoodRequestDto dto) {

        String email = getCurrentUser();

        return ResponseEntity.ok(
                foodService.addFood(dto, email)
        );
    }

    @GetMapping("/available")
    public ResponseEntity<List<Food>> getAvailableFoods() {

        return ResponseEntity.ok(
                foodService.getAvailableFoods()
        );
    }

    @GetMapping("/my")
    public ResponseEntity<?> myFood() {

        String email = getCurrentUser();

        return ResponseEntity.ok(
                foodService.getMyFood(email)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody FoodRequestDto dto) {

        String email = getCurrentUser();

        return ResponseEntity.ok(
                foodService.updateFood(id, dto, email)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        String email = getCurrentUser();

        foodService.deleteFood(id, email);

        return ResponseEntity.ok("Deleted Successfully");
    }

    private String getCurrentUser() {

        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}