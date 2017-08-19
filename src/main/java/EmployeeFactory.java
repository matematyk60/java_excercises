import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import dgodek.company.Task;
import dgodek.company.employee.*;

import java.util.*;
import java.util.stream.IntStream;

class EmployeeFactory {
    private Random generator;
    private final ImmutableList<String> names;
    private final ImmutableList<String> taskNames;
    private final ImmutableList<String> surnames;
    private final ImmutableList<String> domains;
    private final ImmutableList<String> nationalities;
    private final ImmutableList<String> academies;


    EmployeeFactory() {
        this.generator = new Random();
        this.taskNames = ImmutableList.of("pisanie", "czytanie", "logowanie", "parsowanie", "wyliczanie",
                "wyszukwianie", "odliczanie", "usuwanie", "liczenie", "szacowanie", "rejestracja");
        this.names = ImmutableList.of("Andrzej", "Szymon", "Piotr", "Ambroży",
                "Bonifacy", "Bill", "Andżelika", "Kajtek", "Feliks", "Beata", "Robert");
        this.surnames = ImmutableList.of("Szydlo", "Fred", "Kanar", "Ptaszek",
                "Bialy", "Czarny", "Zegar", "Kwarc", "Felek", "Lisc", "Lodek");
        this.domains = ImmutableList.of("gmail.com", "hotmail.com", "mail.com",
                "buziaczek.pl","o2.pl","interia.pl","agh.edu.pl");
        this.nationalities = ImmutableList.of("Poland","Germany","Norway","Czech","Slovakia","Spain","France");
        this.academies = ImmutableList.of("AGH","SGH","UWR","UJ","UW","PWR","PWSZwKrosnie");        
    }

    private int getRandom(int range) {
        return generator.nextInt(range);
    }

    private String getName() {
        return names.get(getRandom(names.size()));
    }

    Task getTask() {
        int tmp = getRandom(taskNames.size());

        return new Task(taskNames.get(tmp), tmp);
    }

    private String getSurname() {
        return surnames.get(getRandom(surnames.size()));
    }

    private String getDomain() {
        return domains.get(getRandom(domains.size()));
    }

    private String getNationality() {
        return nationalities.get(getRandom(nationalities.size()));
    }

    private Sex getSex() {
        return (getRandom(1) == 0)
                ? Sex.MALE
                : Sex.FEMALE;
    }

    private String getAcademy() {
        return academies.get(getRandom(academies.size()));
    }

    Developer getDeveloper() {
        String name = getName();
        String surname = getSurname();
        String nationality = getNationality();
        String email = name+surname+"@"+getDomain();
        Sex sex = getSex();
        String academy = getAcademy();

        return new Developer.Builder(name,surname,email,nationality)
                .sex(sex)
                .academy(academy)
                .build();
    }

    TeamManager getManager(int maxSize) {
        String name = getName();
        String surname = getSurname();
        String nationality = getNationality();
        String email = name+surname+"@"+getDomain();
        Sex sex = getSex();
        String academy = getAcademy();

        return new TeamManager.Builder(name,surname,email,maxSize,nationality)
                .sex(sex)
                .academy(academy)
                .build();
    }

    List<Developer> getDevs(int amount) {
        List<Developer> devs = new ArrayList<>();
        for(int i = 0 ; i < amount ; i++){
            devs.add(getDeveloper());
        }

        return devs;
    }

    List<TeamManager> getManagers(int amount, int maxSize) {
        List<TeamManager> managers = new ArrayList<>();
        for(int i = 0 ; i < amount ; i++){
            managers.add(getManager(maxSize));
        }

        return managers;
    }
}
