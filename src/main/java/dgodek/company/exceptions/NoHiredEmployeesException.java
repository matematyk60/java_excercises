package dgodek.company.exceptions;

public class NoHiredEmployeesException extends RuntimeException {
    public NoHiredEmployeesException() {
        super("You can't assign task to manager with no workers");
    }
}
