package com.example.JobApplication.Job.impl;

import com.example.JobApplication.Job.Job.JobService;
import com.example.JobApplication.Job.Job.Job;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private Long jobid = 0L;

    private List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public String createJob(@RequestBody Job newJob) {
        newJob.setId(jobid++);
        jobs.add(newJob);
        return "Job added successfully";
    }

    @Override
    public Job getJobById(Long id) {
        for(Job job:jobs){
            if(job.getId()==id){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        Optional<Job> jobToRemove = jobs.stream()
                .filter(job -> job.getId().equals(id))
                .findFirst();

        if (jobToRemove.isPresent()) {
            jobs.remove(jobToRemove.get());
            return true;
        }

        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobToUpdate = jobs.stream()
                .filter(job -> job.getId().equals(id))
                .findFirst();

        if (jobToUpdate.isPresent()) {
            Job update = jobToUpdate.get();
            update.setDescription(updatedJob.getDescription());
            update.setLocation(updatedJob.getLocation());
            update.setTitle(updatedJob.getTitle());
            update.setMinSalary(updatedJob.getMinSalary());
            update.setMaxSalary(updatedJob.getMaxSalary());
            return true;
        }

        return false;
    }

}



