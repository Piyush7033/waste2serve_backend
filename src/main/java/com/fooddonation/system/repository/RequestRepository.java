package com.fooddonation.system.repository;

import com.fooddonation.system.entity.Request;
import com.fooddonation.system.entity.User;
import com.fooddonation.system.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    // =========================
    // GET REQUESTS BY RECEIVER
    // =========================
    List<Request> findByReceiver(User receiver);

    // =========================
    // GET REQUESTS BY FOOD
    // =========================
    List<Request> findByFood(Food food);

    // =========================
    // CHECK IF ALREADY REQUESTED
    // =========================
    boolean existsByFoodAndReceiver(Food food, User receiver);

    // =========================
    // OPTIONAL (ONLY IF YOU REALLY NEED ID-BASED CHECK)
    // =========================
    boolean existsByFoodIdAndReceiverId(Long foodId, Long receiverId);
}