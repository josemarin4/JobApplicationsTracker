package app.jobapplicationtracker.exception;

public class DuplicateJobApplicationException extends RuntimeException{

    public DuplicateJobApplicationException(String msg){
        super(msg);
    }
}
