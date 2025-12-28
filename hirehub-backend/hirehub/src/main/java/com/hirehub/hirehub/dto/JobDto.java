package com.hirehub.hirehub.dto;

import java.time.LocalDateTime;

/**
 * JobDto
 *
 *  Single Responsibility Principle (SRP):
 *    - This record is only responsible for carrying job-related data between layers.
 *
 * Open/Closed Principle (OCP):
 *    - It can be extended with new fields if needed without modifying existing behavior.
 */
public record JobDto(
        Long id,
        String title,
        String company,
        String location,
        Double salary,
        String description,
        LocalDateTime createdAt
) {}
