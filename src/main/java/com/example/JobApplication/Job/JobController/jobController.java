package com.example.JobApplication.Job.JobController;

import com.example.JobApplication.Job.Job.JobService;
import com.example.JobApplication.Job.Job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class jobController {

    private JobService jobService;

    public jobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){

       return new ResponseEntity<>( jobService.findAll(),HttpStatus.OK);

    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job newJob) {
        jobService.createJob(newJob);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job =  jobService.getJobById(id);
        if(job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job Posting Deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not Able to find the Job Posting",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id,updatedJob);
        if(updated){
            return new ResponseEntity<>("Job Posting is updated successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Job Posting can not be updated",HttpStatus.NOT_FOUND);
        }
    }
}
