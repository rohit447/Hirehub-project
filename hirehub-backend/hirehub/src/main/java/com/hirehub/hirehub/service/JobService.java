package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.JobDto;
import org.springframework.data.domain.Page;

public interface JobService {

    JobDto createJob(JobDto dto);

    JobDto getJobById(Long id);

    Page<JobDto> getJobs(int page, int size, String search);

    void deleteJob(Long id);
}
