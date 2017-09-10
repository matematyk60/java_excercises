package dgodek.company;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TaskFactory {
    private final List<String> taskNames;
    private final Random generator;

    public TaskFactory() {
        this.generator = new Random();
        this.taskNames = Arrays.asList("pisanie", "czytanie", "logowanie", "parsowanie", "wyliczanie",
                "wyszukwianie", "odliczanie", "usuwanie", "liczenie", "szacowanie", "rejestracja");
    }

    public Task getTask() {
        int tmp = generator.nextInt(taskNames.size());

        return new Task(taskNames.get(tmp), tmp+1);
    }
}
