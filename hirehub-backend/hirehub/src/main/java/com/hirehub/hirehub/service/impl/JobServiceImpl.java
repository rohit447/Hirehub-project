package com.hirehub.hirehub.service.impl;

import com.hirehub.hirehub.dto.JobDto;
import com.hirehub.hirehub.entity.Job;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.mapper.JobMapper;
import com.hirehub.hirehub.repository.JobRepository;
import com.hirehub.hirehub.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * JobServiceImpl
 *
 *  Follows **Single Responsibility Principle (SRP)**:
 *    - This class is only responsible for implementing business logic
 *      related to Job operations (create, read, delete, search).
 *    - It does not handle HTTP requests (controller responsibility) or persistence
 *      details beyond repository usage.
 *
 *  Follows **Open/Closed Principle (OCP)**:
 *    - New job-related features (e.g., filtering, sorting) can be added
 *      without modifying existing methods, by extending repository or mapper logic.
 *
 *  Follows **Liskov Substitution Principle (LSP)**:
 *    - Any other implementation of JobService can replace this class
 *      without breaking the system, since it adheres to the JobService contract.
 *
 *  Follows **Dependency Inversion Principle (DIP)**:
 *    - Depends on abstractions (JobRepository, JobMapper, JobService interface)
 *      instead of concrete implementations, ensuring loose coupling.
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    /**
     * Create a new Job
     *
     *  SRP: Handles only job creation logic.
     *  OCP: If new validation or rules are added, they can be extended
     *    without modifying this method directly (e.g., decorator or validator).
     */
    @Override
    public JobDto createJob(JobDto dto) {
        Job job = JobMapper.toEntity(dto);
        return JobMapper.toDto(jobRepository.save(job));
    }

    /**
     * Get Job by ID
     *
     *  SRP: Only responsible for fetching a job by ID.
     *  LSP: Any subclass of JobRepository can be substituted without breaking logic.
     *  DIP: Relies on repository abstraction for data access.
     */
    @Override
    public JobDto getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        return JobMapper.toDto(job);
    }

    /**
     * Get all Jobs with pagination and optional search
     *
     *  SRP: Handles only job retrieval logic with pagination and search.
     *  OCP: Supports extension (e.g., advanced filters, sorting) without modifying
     *    this method — changes can be handled in repository layer.
     *  DIP: Uses repository abstraction for querying.
     */
    @Override
    public Page<JobDto> getJobs(int page, int size, String search) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Job> result;

        if(search != null && !search.isEmpty()){
            result = jobRepository.findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(
                    search, search, pageable);
        } else {
            result = jobRepository.findAll(pageable);
        }

        return result.map(JobMapper::toDto);
    }

    /**
     * Delete Job by ID
     *
     *  SRP: Only responsible for deletion logic.
     *  DIP: Relies on repository abstraction for deletion.
     *  OCP: If soft-delete or audit logging is introduced, it can be extended
     *    without modifying this method directly.
     */
    @Override
    public void deleteJob(Long id) {
        if(!jobRepository.existsById(id))
            throw new ResourceNotFoundException("Job not found");

        jobRepository.deleteById(id);
    }
}
