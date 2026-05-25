package com.fooddonation.system.service;

import com.fooddonation.system.entity.*;
import com.fooddonation.system.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestServiceImpl
        implements RequestService {

    @Autowired
    private RequestRepository requestRepo;

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmailService emailService;

    // =====================================================
    // CREATE REQUEST
    // =====================================================

    @Override
    public String createRequest(

            Long foodId,

            String email,

            String message
    ) {

        // ================= USER =================

        User receiver =
                userRepo.findByEmail(email)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Receiver not found"
                                )
                        );

        // ================= FOOD =================

        Food food =
                foodRepo.findById(foodId)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Food not found"
                                )
                        );

        // ================= FOOD STATUS CHECK =================

        if (food.getStatus()
                != FoodStatus.AVAILABLE) {

            throw new RuntimeException(
                    "Food is not available"
            );
        }

        // ================= DUPLICATE CHECK =================

        boolean alreadyRequested =

                requestRepo.existsByFoodIdAndReceiverId(

                        food.getId(),

                        receiver.getId()
                );

        if (alreadyRequested) {

            throw new RuntimeException(
                    "You already requested this food"
            );
        }

        // ================= DEFAULT MESSAGE =================

        if (message == null
                || message.trim().isEmpty()) {

            message =
                    "Food request submitted";
        }

        // ================= CREATE REQUEST =================

        Request request = new Request();

        request.setFood(food);

        request.setReceiver(receiver);

        request.setMessage(message);

        request.setStatus(
                RequestStatus.REQUESTED
        );

        request.setRequestTime(
                LocalDateTime.now()
        );

        // ================= UPDATE FOOD =================

        food.setStatus(
                FoodStatus.REQUESTED
        );

        // ================= SAVE =================

        foodRepo.save(food);

        requestRepo.save(request);

        // ================= EMAIL =================

        try {

            String adminEmail =
                    "singhpiyush7033457@gmail.com";

            String subject =
                    "New Food Request - FeedCycle";

            String body =

                    "Hello Admin,\n\n"

                            + "A receiver has requested food.\n\n"

                            + "Receiver Name : "
                            + receiver.getName()
                            + "\n"

                            + "Receiver Email : "
                            + receiver.getEmail()
                            + "\n"

                            + "Food Title : "
                            + food.getTitle()
                            + "\n"

                            + "Quantity : "
                            + food.getQuantity()
                            + "\n"

                            + "Location : "
                            + food.getLocation()
                            + "\n"

                            + "Message : "
                            + message
                            + "\n\n"

                            + "Please review the request.\n\n"

                            + "FeedCycle System";

            emailService.sendEmail(

                    adminEmail,

                    subject,

                    body
            );

        } catch (Exception e) {

            System.out.println(

                    "Email sending failed : "
                            + e.getMessage()
            );
        }

        return "Request created successfully";
    }

    // =====================================================
    // GET MY REQUESTS
    // =====================================================

    @Override
    public List<Request> getMyRequests(

            String email
    ) {

        User receiver =
                userRepo.findByEmail(email)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        return requestRepo.findByReceiver(
                receiver
        );
    }

    // =====================================================
    // GET ALL REQUESTS
    // =====================================================

    @Override
    public List<Request> getAllRequests() {

        return requestRepo.findAll();
    }

    // =====================================================
    // ACCEPT REQUEST
    // =====================================================

    @Override
    public String acceptRequest(

            Long requestId
    ) {

        Request request =
                requestRepo.findById(requestId)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Request not found"
                                )
                        );

        // ================= STATUS UPDATE =================

        request.setStatus(
                RequestStatus.ACCEPTED
        );

        // ================= FOOD UPDATE =================

        Food food = request.getFood();

        food.setStatus(
                FoodStatus.ACCEPTED
        );

        // ================= SAVE =================

        foodRepo.save(food);

        requestRepo.save(request);

        return "Request accepted successfully";
    }

    // =====================================================
    // REJECT REQUEST
    // =====================================================

    @Override
    public String rejectRequest(

            Long requestId
    ) {

        Request request =
                requestRepo.findById(requestId)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Request not found"
                                )
                        );

        // ================= STATUS UPDATE =================

        request.setStatus(
                RequestStatus.REJECTED
        );

        // ================= FOOD UPDATE =================

        Food food = request.getFood();

        food.setStatus(
                FoodStatus.AVAILABLE
        );

        // ================= SAVE =================

        foodRepo.save(food);

        requestRepo.save(request);

        return "Request rejected successfully";
    }

    // =====================================================
    // CANCEL REQUEST
    // =====================================================

    @Override
    public String cancelRequest(

            Long requestId,

            String email
    ) {

        // ================= USER =================

        User receiver =
                userRepo.findByEmail(email)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        // ================= REQUEST =================

        Request request =
                requestRepo.findById(requestId)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Request not found"
                                )
                        );

        // ================= OWNER CHECK =================

        if (!request.getReceiver()
                .getId()
                .equals(receiver.getId())) {

            throw new RuntimeException(
                    "You cannot cancel this request"
            );
        }

        // ================= STATUS CHECK =================

        if (request.getStatus()
                != RequestStatus.REQUESTED) {

            throw new RuntimeException(
                    "Only requested requests can be cancelled"
            );
        }

        // ================= UPDATE REQUEST =================

        request.setStatus(
                RequestStatus.CANCELLED
        );

        // ================= UPDATE FOOD =================

        Food food = request.getFood();

        food.setStatus(
                FoodStatus.AVAILABLE
        );

        // ================= SAVE =================

        foodRepo.save(food);

        requestRepo.save(request);

        return "Request cancelled successfully";
    }
}