package com.fooddonation.system.service;

import com.fooddonation.system.entity.Request;

import java.util.List;

public interface RequestService {

    // =====================================================
    // CREATE REQUEST
    // =====================================================

    String createRequest(

            Long foodId,

            String email,

            String message
    );

    // =====================================================
    // GET MY REQUESTS
    // =====================================================

    List<Request> getMyRequests(

            String email
    );

    // =====================================================
    // GET ALL REQUESTS
    // =====================================================

    List<Request> getAllRequests();

    // =====================================================
    // ACCEPT REQUEST
    // =====================================================

    String acceptRequest(

            Long requestId
    );

    // =====================================================
    // REJECT REQUEST
    // =====================================================

    String rejectRequest(

            Long requestId
    );

    // =====================================================
    // CANCEL REQUEST
    // =====================================================

    String cancelRequest(

            Long requestId,

            String email
    );
}