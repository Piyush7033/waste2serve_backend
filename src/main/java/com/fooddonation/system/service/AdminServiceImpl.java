package com.fooddonation.system.service;

import com.fooddonation.system.entity.Food;
import com.fooddonation.system.entity.FoodStatus;
import com.fooddonation.system.entity.User;

import com.fooddonation.system.repository.FoodRepository;
import com.fooddonation.system.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl
        implements AdminService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoodRepository foodRepo;

    // ==========================================
    // GET ALL USERS
    // ==========================================
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // ==========================================
    // GET ACTIVE USERS
    // ==========================================
    @Override
    public List<User> getActiveUsers() {
        return userRepo.findByActive(true);
    }

    // ==========================================
    // GET DISABLED USERS
    // ==========================================
    @Override
    public List<User> getDisabledUsers() {
        return userRepo.findByActive(false);
    }

    // ==========================================
    // TOGGLE USER STATUS
    // ==========================================
    @Override
    public void toggleUser(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: " + id
                        )
                );

        user.setActive(!user.isActive());

        userRepo.save(user);
    }

    // ==========================================
    // ENABLE USER
    // ==========================================
    @Override
    public void enableUser(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        if (!user.isActive()) {

            user.setActive(true);

            userRepo.save(user);
        }
    }

    // ==========================================
    // DISABLE USER
    // ==========================================
    @Override
    public void disableUser(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        if (user.isActive()) {

            user.setActive(false);

            userRepo.save(user);
        }
    }

    // ==========================================
    // DELETE USER
    // ==========================================
    @Override
    public void deleteUser(Long id) {

        if (!userRepo.existsById(id)) {

            throw new RuntimeException(
                    "User not found with id: " + id
            );
        }

        userRepo.deleteById(id);
    }

    // ==========================================
    // GET ALL FOOD
    // ==========================================
    @Override
    public List<Food> getAllFood() {

        return foodRepo.findAll();
    }

    // ==========================================
    // DELETE FOOD
    // ==========================================
    @Override
    public void deleteFood(Long id) {

        if (!foodRepo.existsById(id)) {

            throw new RuntimeException(
                    "Food not found with id: " + id
            );
        }

        foodRepo.deleteById(id);
    }

    // ==========================================
    // ACCEPT FOOD
    // ==========================================
    @Override
    public Food acceptFood(Long id) {

        Food food = foodRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Food not found with id: " + id
                        )
                );

        food.setStatus(FoodStatus.ACCEPTED);

        return foodRepo.save(food);
    }

    // ==========================================
    // REJECT FOOD
    // ==========================================
    @Override
    public Food rejectFood(Long id) {

        Food food = foodRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Food not found with id: " + id
                        )
                );

        food.setStatus(FoodStatus.REJECTED);

        return foodRepo.save(food);
    }

    // ==========================================
    // TOTAL USERS
    // ==========================================
    @Override
    public long totalUsers() {

        return userRepo.count();
    }

    // ==========================================
    // TOTAL ACTIVE USERS
    // ==========================================
    @Override
    public long totalActiveUsers() {

        return userRepo.countByActive(true);
    }

    // ==========================================
    // TOTAL DISABLED USERS
    // ==========================================
    @Override
    public long totalDisabledUsers() {

        return userRepo.countByActive(false);
    }

    // ==========================================
    // TOTAL FOOD ITEMS
    // ==========================================
    @Override
    public long totalFoodItems() {

        return foodRepo.count();
    }
}