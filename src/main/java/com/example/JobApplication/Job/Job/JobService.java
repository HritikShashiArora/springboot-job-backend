package com.example.JobApplication.Job.Job;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    String createJob(@RequestBody Job newJob);


    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
