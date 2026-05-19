package com.fooddonation.system.controller;

import com.fooddonation.system.entity.Donation;
import com.fooddonation.system.service.DonationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "http://localhost:5173")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping("/add")
    public ResponseEntity<?> addDonation(@RequestBody Donation donation) {

        return ResponseEntity.ok(
                donationService.saveDonation(donation)
        );
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyDonations() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                donationService.getDonationHistoryByUser(email)
        );
    }

    @GetMapping("/all")
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public Optional<Donation> getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDonation(@PathVariable Long id) {

        donationService.deleteDonation(id);
        return "Donation deleted successfully";
    }

    @GetMapping("/status/{status}")
    public List<Donation> getDonationsByStatus(@PathVariable String status) {
        return donationService.getDonationsByStatus(status);
    }

    @GetMapping("/location/{location}")
    public List<Donation> getDonationsByLocation(@PathVariable String location) {
        return donationService.getDonationsByLocation(location);
    }

    @GetMapping("/search/{foodName}")
    public List<Donation> searchFoodByName(@PathVariable String foodName) {
        return donationService.searchFoodByName(foodName);
    }

    @PutMapping("/update-status/{id}")
    public Donation updateDonationStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return donationService.updateDonationStatus(id, status);
    }
}