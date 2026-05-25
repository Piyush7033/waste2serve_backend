package com.fooddonation.system.service;

import com.fooddonation.system.entity.Donation;
import com.fooddonation.system.repository.DonationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    // =========================
    // SAVE DONATION
    // =========================
    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    // =========================
    // GET ALL DONATIONS (ADMIN)
    // =========================
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // =========================
    // GET DONATION HISTORY BY USER (FIXED)
    // =========================
    public List<Donation> getDonationHistoryByUser(String email) {
        return donationRepository.findByDonorEmail(email);
    }

    // =========================
    // GET DONATION BY ID
    // =========================
    public Optional<Donation> getDonationById(Long id) {
        return donationRepository.findById(id);
    }

    // =========================
    // DELETE DONATION
    // =========================
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    // =========================
    // GET BY STATUS
    // =========================
    public List<Donation> getDonationsByStatus(String status) {
        return donationRepository.findByStatus(status);
    }

    // =========================
    // GET BY LOCATION
    // =========================
    public List<Donation> getDonationsByLocation(String location) {
        return donationRepository.findByLocation(location);
    }

    // =========================
    // SEARCH FOOD
    // =========================
    public List<Donation> searchFoodByName(String foodName) {
        return donationRepository.findByFoodNameContainingIgnoreCase(foodName);
    }

    // =========================
    // UPDATE STATUS
    // =========================
    public Donation updateDonationStatus(Long id, String status) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        donation.setStatus(status);

        return donationRepository.save(donation);
    }

    public List<Donation> getDonationHistory(Long userId) {
        return donationRepository.findByUserId(userId);

    }

}