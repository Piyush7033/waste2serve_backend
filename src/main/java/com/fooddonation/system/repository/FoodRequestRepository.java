package com.fooddonation.system.repository;

import com.fooddonation.system.entity.FoodRequest;
import com.fooddonation.system.entity.FoodStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRequestRepository extends JpaRepository<FoodRequest, Long> {


    List<FoodRequest> findByDonorId(Long donorId);


    List<FoodRequest> findByReceiverId(Long receiverId);


    List<FoodRequest> findByStatus(FoodStatus status);


    List<FoodRequest> findByFoodId(Long foodId);


    List<FoodRequest> findByStatusOrderByCreatedAtDesc(FoodStatus status);


    List<FoodRequest> findByReceiverIdAndStatus(Long receiverId, FoodStatus status);


    List<FoodRequest> findByDonorIdAndStatus(Long donorId, FoodStatus status);
}