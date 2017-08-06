package dgodek.company;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class Task {
    private final String name;
    private final int unitsOfWork;

    public Task(String name, int unitsOfWork) {
        this.name = name;
        this.unitsOfWork = unitsOfWork;
    }

    public String getName() {
        return name;
    }

    public int getunitsOfWork() {
        return unitsOfWork;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", unitsOfWork=" + unitsOfWork +
                '}';
    }
}
