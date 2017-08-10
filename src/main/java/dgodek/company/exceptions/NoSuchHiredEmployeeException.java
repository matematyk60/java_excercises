package dgodek.company.exceptions;

public class NoSuchHiredEmployeeException extends RuntimeException {

    public NoSuchHiredEmployeeException() {

        super("There is no such hired employee!");

    }
}
