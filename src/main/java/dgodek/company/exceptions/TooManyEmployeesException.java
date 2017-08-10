package dgodek.company.exceptions;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class TooManyEmployeesException extends RuntimeException {
    public TooManyEmployeesException() {
        super("No available employee slot");
    }
}
