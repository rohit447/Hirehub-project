package com.hirehub.hirehub.repository;

import com.hirehub.hirehub.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;

/**
 * JobRepository
 *
 *  Follows **Single Responsibility Principle (SRP)**:
 *    - Responsible only for data access operations related to Job entity.
 *    - It does not contain business logic or request handling.
 *
 *  Follows **Open/Closed Principle (OCP)**:
 *    - Can be extended with new query methods without modifying existing ones.
 *    - Example: Adding new finder methods for filtering jobs by salary or company.
 *
 *  Follows **Liskov Substitution Principle (LSP)**:
 *    - Any subclass or alternative implementation of JpaRepository can replace this
 *      without breaking functionality, since it adheres to the repository contract.
 *
 *  Follows **Interface Segregation Principle (ISP)**:
 *    - Provides only the methods needed for Job entity operations.
 *    - Clients (services) depend only on relevant repository methods.
 *
 *  Follows **Dependency Inversion Principle (DIP)**:
 *    - Higher-level modules (services/controllers) depend on this abstraction
 *      instead of directly handling database queries.
 */
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * Custom query method to search jobs by title or location (case-insensitive).
     *
     *  SRP: Only responsible for defining query signatures.
     *  OCP: Easily extendable with more query methods without modifying existing ones.
     */
    Page<Job> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(
            String title,
            String location,
            Pageable pageable
    );
}
