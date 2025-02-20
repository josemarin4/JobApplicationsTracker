package app.jobapplicationtracker.service;

import app.jobapplicationtracker.exception.JobApplicationNotFoundException;
import app.jobapplicationtracker.model.JobApplication;
import app.jobapplicationtracker.repository.JobApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplication getApplication(UUID applicationId){

        return jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new JobApplicationNotFoundException(
                        "Application with id: " + applicationId + " not found."));
    }

}
