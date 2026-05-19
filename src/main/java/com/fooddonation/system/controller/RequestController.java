package com.fooddonation.system.controller;

import com.fooddonation.system.service.RequestService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "http://localhost:3000"
        }
)
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/create/{foodId}")
    @PreAuthorize("hasRole('RECEIVER')")
    public ResponseEntity<?> createRequest(

            @PathVariable Long foodId,

            @RequestBody(required = false)
            String message,

            Principal principal
    ) {

        try {

            if (message == null || message.trim().isEmpty()) {

                message =
                        "Food request submitted";
            }

            return ResponseEntity.ok(

                    requestService.createRequest(

                            foodId,

                            principal.getName(),

                            message
                    )
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(

                    "Failed to create request : "
                            + e.getMessage()
            );
        }
    }

    @GetMapping("/my")
    @PreAuthorize(
            "hasAnyRole('RECEIVER','DONOR','ADMIN')"
    )
    public ResponseEntity<?> myRequests(

            Principal principal
    ) {

        try {

            return ResponseEntity.ok(

                    requestService.getMyRequests(
                            principal.getName()
                    )
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(

                    "Failed to fetch requests : "
                            + e.getMessage()
            );
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllRequests() {

        try {

            return ResponseEntity.ok(

                    requestService.getAllRequests()
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(

                    "Failed to fetch all requests : "
                            + e.getMessage()
            );
        }
    }

    @PutMapping("/{id}/accept")
    @PreAuthorize(
            "hasAnyRole('DONOR','ADMIN')"
    )
    public ResponseEntity<?> acceptRequest(

            @PathVariable Long id
    ) {

        try {

            return ResponseEntity.ok(

                    requestService.acceptRequest(id)
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(

                    "Failed to accept request : "
                            + e.getMessage()
            );
        }
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize(
            "hasAnyRole('DONOR','ADMIN')"
    )
    public ResponseEntity<?> rejectRequest(

            @PathVariable Long id
    ) {

        try {

            return ResponseEntity.ok(

                    requestService.rejectRequest(id)
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(

                    "Failed to reject request : "
                            + e.getMessage()
            );
        }
    }
}