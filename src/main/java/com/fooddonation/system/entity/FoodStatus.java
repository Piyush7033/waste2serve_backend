package com.fooddonation.system.entity;

public enum FoodStatus {

    AVAILABLE,   // Food added by donor and visible to receivers

    REQUESTED,   // Receiver has requested the food

    PENDING,

    APPROVED,

    REJECTED,

    ACCEPTED,

    COMPLETED
}