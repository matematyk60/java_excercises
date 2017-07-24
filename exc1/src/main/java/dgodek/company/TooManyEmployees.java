package dgodek.company;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class TooManyEmployees extends RuntimeException {
    public TooManyEmployees() {
        super("No available employee slot");
    }
}
