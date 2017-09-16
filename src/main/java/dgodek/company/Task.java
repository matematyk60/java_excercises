package dgodek.company;

import java.io.Serializable;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class Task implements Serializable {
    private final String name;
    private final int unitsOfWork;

    public Task(String name, int unitsOfWork) {
        this.name = name;
        this.unitsOfWork = unitsOfWork;
    }

    public String getName() {
        return name;
    }

    public int getUnitsOfWork() {
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
