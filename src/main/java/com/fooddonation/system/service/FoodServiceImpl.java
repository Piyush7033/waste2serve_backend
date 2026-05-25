package com.fooddonation.system.service;

import com.fooddonation.system.dto.FoodRequestDto;
import com.fooddonation.system.entity.*;
import com.fooddonation.system.repository.DonationRepository;
import com.fooddonation.system.repository.FoodRepository;
import com.fooddonation.system.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DonationRepository donorRepo;

    // ================= ADD FOOD =================
    @Override
    public Food addFood(FoodRequestDto dto, String email) {

        User donor = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Food food = new Food();
        food.setTitle(dto.getTitle());
        food.setDescription(dto.getDescription());
        food.setQuantity(dto.getQuantity());
        food.setLocation(dto.getLocation());
        food.setExpiryTime(dto.getExpiryTime());
        food.setStatus(FoodStatus.AVAILABLE);
        food.setDonor(donor);

        Donation donation=new Donation();
        donation.setDonorEmail(email);
        donation.setFoodName(dto.getTitle());
        donation.setQuantity(dto.getQuantity());
        donation.setLocation(dto.getLocation());
        donation.setDonatedAt(LocalDateTime.now());
        donation.setUserId(donor.getId());

        donation.setStatus("AVAILABLE");


        Food foodResponse = foodRepo.save(food);
        donorRepo.save(donation);
        return foodResponse;

    }

    // ================= AVAILABLE FOODS =================
    @Override
    public List<Food> getAvailableFoods() {

        return foodRepo.findByStatusAndExpiryTimeAfter(
                FoodStatus.AVAILABLE,
                LocalDateTime.now()
        );
    }

    // ================= MY FOOD =================
    @Override
    public List<Food> getMyFood(String email) {

        User donor = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return foodRepo.findByDonor(donor);
    }

    // ================= UPDATE FOOD (SECURED) =================
    @Override
    public Food updateFood(Long id, FoodRequestDto dto, String email) {

        User donor = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Food food = foodRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        // 🔐 OWNERSHIP CHECK
        if (!food.getDonor().getId().equals(donor.getId())) {
            throw new RuntimeException("You are not allowed to update this food");
        }

        food.setTitle(dto.getTitle());
        food.setDescription(dto.getDescription());
        food.setQuantity(dto.getQuantity());
        food.setLocation(dto.getLocation());
        food.setExpiryTime(dto.getExpiryTime());

        return foodRepo.save(food);
    }

    // ================= DELETE FOOD (SECURED) =================
    @Override
    public void deleteFood(Long id, String email) {

        User donor = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Food food = foodRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        // 🔐 OWNERSHIP CHECK
        if (!food.getDonor().getId().equals(donor.getId())) {
            throw new RuntimeException("You are not allowed to delete this food");
        }

        foodRepo.delete(food);
    }
}