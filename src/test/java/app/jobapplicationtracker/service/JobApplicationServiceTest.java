package app.jobapplicationtracker.service;

import app.jobapplicationtracker.exception.JobApplicationNotFoundException;
import app.jobapplicationtracker.model.JobApplication;
import app.jobapplicationtracker.repository.JobApplicationRepository;
import app.jobapplicationtracker.util.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    private JobApplication jobApplication;

    private LocalDate today;

    @BeforeEach
    public void setUp() {
        today = LocalDate.now();
        jobApplication = new JobApplication();
        jobApplication.setApplicationId(UUID.randomUUID());
        jobApplication.setApplicationTitle("Software Engineer");
        jobApplication.setCompanyName("Google");
        jobApplication.setStatus(Status.APPLIED);
        jobApplication.setDateApplied(today);
    }

    @Test
    public void shouldReturnJobApplicationWhenIdExists() {
        UUID id = jobApplication.getApplicationId();

        when(jobApplicationRepository.findById(id)).thenReturn(Optional.of(jobApplication));

        JobApplication app = jobApplicationService.getApplication(id);

        assertThat(app).isNotNull();
        assertThat(app.getApplicationId()).isEqualTo(id);
        assertThat(app.getApplicationTitle()).isEqualTo(jobApplication.getApplicationTitle());
        assertThat(app.getStatus()).isEqualTo(Status.APPLIED);
        assertThat(app.getCompanyName()).isEqualTo(jobApplication.getCompanyName());
        assertThat(app.getDateApplied()).isEqualTo(today);

        verify(jobApplicationRepository).findById(id);
    }

    @Test
    public void shouldThrowExceptionWhenJobApplicationNotFound(){

        UUID nonExistentId = UUID.randomUUID();

        when(jobApplicationRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> jobApplicationService.getApplication(nonExistentId))
                .isInstanceOf(JobApplicationNotFoundException.class)
                .hasMessageContaining("Application with id: " + nonExistentId + " not found.");

        verify(jobApplicationRepository).findById(nonExistentId);
    }
}