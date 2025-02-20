package app.jobapplicationtracker.exception;

public class JobApplicationNotFoundException extends RuntimeException{

    public JobApplicationNotFoundException(String msg){
        super(msg);
    }
}
