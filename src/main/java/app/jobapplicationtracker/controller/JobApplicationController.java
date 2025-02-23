package app.jobapplicationtracker.controller;

import app.jobapplicationtracker.model.JobApplication;
import app.jobapplicationtracker.service.JobApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@AllArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;


    @GetMapping("/{applicationId}")
    public ResponseEntity<JobApplication> getApplication(@PathVariable UUID applicationId){

        return ResponseEntity.ok(jobApplicationService.getApplication(applicationId));
    }

    @PostMapping()
    public ResponseEntity<JobApplication> addApplication(@RequestBody JobApplication jobApplication){

        return ResponseEntity.ok(jobApplicationService.addApplication(jobApplication));
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<JobApplication> updateApplication(@PathVariable UUID applicationId,
                                                               @RequestBody JobApplication jobApplication){

        return ResponseEntity.ok(jobApplicationService.updateApplication(applicationId, jobApplication));
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<JobApplication> deleteApplication(@PathVariable UUID applicationId){

        return ResponseEntity.ok(jobApplicationService.deleteApplication(applicationId));
    }
}
