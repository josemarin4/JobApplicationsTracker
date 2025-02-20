package app.jobapplicationtracker.controller;

import app.jobapplicationtracker.model.JobApplication;
import app.jobapplicationtracker.service.JobApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@AllArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;


    @GetMapping("/{applicationId}")
    private ResponseEntity<JobApplication> getApplication(@PathVariable UUID applicationId){

        return ResponseEntity.ok(jobApplicationService.getApplication(applicationId));
    }
}
