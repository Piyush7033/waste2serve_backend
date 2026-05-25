package com.fooddonation.system.repository;

import com.fooddonation.system.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    // =========================
    // GET BY DONOR EMAIL (MAIN FIX)
    // =========================
    List<Donation> findByDonorEmail(String donorEmail);
    List<Donation> findByUserId(long userId);

    // =========================
    // GET BY STATUS
    // =========================
    List<Donation> findByStatus(String status);

    // =========================
    // GET BY LOCATION
    // =========================
    List<Donation> findByLocation(String location);

    // =========================
    // SEARCH FOOD NAME
    // =========================
    List<Donation> findByFoodNameContainingIgnoreCase(String foodName);

    // ✅ FIXED METHOD (IMPORTANT)
//
}