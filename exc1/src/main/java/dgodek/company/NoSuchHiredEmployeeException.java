package dgodek.company;

public class NoSuchHiredEmployeeException extends RuntimeException {

    public NoSuchHiredEmployeeException() {

        super("There is no such hired employee!");

    }
}
