package app.jobapplicationtracker.model;

import app.jobapplicationtracker.util.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID applicationId;
    private String companyName;
    private String applicationTitle;
    private Status status;
    private LocalDate dateApplied;
}
