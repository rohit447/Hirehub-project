package com.hirehub.hirehub.mapper;

import com.hirehub.hirehub.dto.JobDto;
import com.hirehub.hirehub.entity.Job;

import java.time.LocalDateTime;

/**
 * JobMapper
 *
 *  Follows **Single Responsibility Principle (SRP)**:
 *    - Responsible only for mapping between Job entity and JobDto.
 *    - It does not handle persistence, business logic, or request processing.
 *
 *  Follows **Open/Closed Principle (OCP)**:
 *    - Can be extended with new mapping logic (e.g., partial DTOs, custom fields)
 *      without modifying existing methods.
 *
 *  Follows **Liskov Substitution Principle (LSP)**:
 *    - Any alternative mapper implementation can replace this class without
 *      breaking functionality, as long as it adheres to the same contract.
 *
 *  Follows **Interface Segregation Principle (ISP)**:
 *    - Provides only relevant mapping methods (toDto, toEntity).
 *    - Clients are not forced to depend on unused methods.
 *
 *  Follows **Dependency Inversion Principle (DIP)**:
 *    - Higher-level modules (services/controllers) depend on this abstraction
 *      for mapping, not on direct entity/DTO construction.
 */
public class JobMapper {

    /**
     * Convert Job entity to JobDto
     *
     *  SRP: Handles only conversion from entity to DTO.
     *  OCP: If new fields are added in Job, mapping can be extended
     *    without changing existing logic.
     */
    public static JobDto toDto(Job job){
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                job.getLocation(),
                job.getSalary(),
                job.getDescription(),
                job.getCreatedAt()
        );
    }

    /**
     * Convert JobDto to Job entity
     *
     *  SRP: Handles only conversion from DTO to entity.
     *  OCP: Supports extension (e.g., mapping additional fields or validation)
     *    without modifying existing code.
     */
    public static Job toEntity(JobDto dto){
        return Job.builder()
                .title(dto.title())
                .company(dto.company())
                .location(dto.location())
                .salary(dto.salary())
                .description(dto.description())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
