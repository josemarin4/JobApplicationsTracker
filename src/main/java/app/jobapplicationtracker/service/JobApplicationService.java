package app.jobapplicationtracker.service;

import app.jobapplicationtracker.exception.DuplicateJobApplicationException;
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

    public JobApplication addApplication(JobApplication jobApplication){

        boolean exists = jobApplicationRepository.existsById(jobApplication.getApplicationId());

        if(exists){
            throw new DuplicateJobApplicationException(
                    "Job application with id " + jobApplication.getApplicationId() + " already exists.");
        }

        return jobApplicationRepository.save(jobApplication);
    }

    public JobApplication updateApplication(UUID applicationId, JobApplication jobApplication){

        JobApplication appToUpdate = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new JobApplicationNotFoundException(
                        "Application with id: " + applicationId + " not found."));

        appToUpdate.setApplicationTitle(jobApplication.getApplicationTitle());
        appToUpdate.setStatus(jobApplication.getStatus());
        appToUpdate.setCompanyName(jobApplication.getCompanyName());
        appToUpdate.setDateApplied(jobApplication.getDateApplied());

        jobApplicationRepository.save(appToUpdate);

        return appToUpdate;
    }

    public JobApplication deleteApplication(UUID applicationId){

        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new JobApplicationNotFoundException(
                    "Job application with id " + applicationId + " not found."));

        jobApplicationRepository.delete(application);

        return application;
    }
}
