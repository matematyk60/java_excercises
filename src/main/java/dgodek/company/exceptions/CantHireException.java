package dgodek.company.exceptions;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class CantHireException extends RuntimeException {
    public CantHireException() {
        super("This employee cannot be hired to this manager");
    }
}
