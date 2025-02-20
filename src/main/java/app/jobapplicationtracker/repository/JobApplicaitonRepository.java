package app.jobapplicationtracker.repository;

import app.jobapplicationtracker.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobApplicaitonRepository extends JpaRepository<JobApplication, UUID> {
}
