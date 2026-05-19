package com.fooddonation.system.service;

import com.fooddonation.system.entity.Food;
import com.fooddonation.system.entity.User;

import java.util.List;

public interface AdminService {

    // ==========================================
    // USER MANAGEMENT
    // ==========================================

    List<User> getAllUsers();

    List<User> getActiveUsers();

    List<User> getDisabledUsers();

    void toggleUser(Long id);

    void enableUser(Long id);

    void disableUser(Long id);

    void deleteUser(Long id);

    // ==========================================
    // FOOD MANAGEMENT
    // ==========================================

    List<Food> getAllFood();

    void deleteFood(Long id);

    // ✅ NEW
    Food acceptFood(Long id);

    // ✅ NEW
    Food rejectFood(Long id);

    // ==========================================
    // ADMIN ANALYTICS
    // ==========================================

    long totalUsers();

    long totalActiveUsers();

    long totalDisabledUsers();

    long totalFoodItems();
}