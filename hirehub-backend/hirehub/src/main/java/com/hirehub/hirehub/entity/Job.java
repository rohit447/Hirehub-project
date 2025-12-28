package com.hirehub.hirehub.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Job Entity
 *
 * Single Responsibility Principle (SRP):
 *    - Represents only the Job domain model and its persistence mapping.
 *
 *  Open/Closed Principle (OCP):
 *    - Can be extended with new fields or annotations without modifying existing behavior.
 */
@Entity
@Table(name = "jobs", indexes = {
        @Index(name = "idx_title_location", columnList = "title, location")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private Double salary;

    @Column(length = 2000)
    private String description;

    private LocalDateTime createdAt;
}
