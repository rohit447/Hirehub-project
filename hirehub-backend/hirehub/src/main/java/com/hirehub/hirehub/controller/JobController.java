package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.dto.JobDto;
import com.hirehub.hirehub.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

/**
 * JobController
 *
 *  Follows **Single Responsibility Principle (SRP)**:
 *    - This controller is only responsible for handling HTTP requests
 *      related to Job resources (create, read, delete).
 *    - It does not contain business logic; that responsibility is delegated
 *      to the JobService.
 *
 *  Follows **Dependency Inversion Principle (DIP)**:
 *    - Depends on the abstraction (JobService interface/contract) rather than
 *      concrete implementation.
 *    - Spring injects the dependency, making the controller loosely coupled.
 */
@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class JobController {

    // Dependency Injection ensures DIP compliance
    private final JobService jobService;

    /**
     * Create a new Job
     *
     *  SRP: This method only handles the request mapping and delegates
     *    job creation logic to JobService.
     *  Open/Closed Principle (OCP): If new job creation rules are added,
     *    they can be handled in JobService without modifying this controller.
     */
    @PostMapping
    public JobDto create(@RequestBody JobDto dto){
        return jobService.createJob(dto);
    }

    /**
     * Get Job by ID
     *
     *  SRP: Only responsible for mapping the GET request and delegating
     *    retrieval logic to JobService.
     * Liskov Substitution Principle (LSP): Any subclass of JobService
     *    can be substituted without breaking functionality.
     */
    @GetMapping("/{id}")
    public JobDto getById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    /**
     * Get all Jobs with pagination and optional search
     *
     *  SRP: Handles request parameters and delegates fetching logic.
     *  OCP: Supports extension (e.g., filtering, sorting) without modifying
     *    this controller — changes happen in JobService.
     */
    @GetMapping
    public Page<JobDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ){
        return jobService.getJobs(page, size, search);
    }

    /**
     * Delete Job by ID
     *
     *  SRP: Only maps DELETE request and delegates deletion logic.
     *  DIP: Relies on JobService abstraction for deletion.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        jobService.deleteJob(id);
    }
}
