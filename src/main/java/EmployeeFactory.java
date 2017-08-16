import dgodek.company.Task;
import dgodek.company.employee.*;

import java.util.*;
import java.util.stream.IntStream;

public class EmployeeFactory {
    private Random generator;


    public EmployeeFactory() {
        this.generator = new Random();
    }

    private int getRandom(int range) {
        return generator.nextInt(range);
    }

    public String getName() {
        List<String> names = Arrays.asList("Andrzej", "Szymon", "Piotr", "Ambroży",
                "Bonifacy", "Bill", "Andżelika", "Kajtek", "Feliks", "Beata", "Robert");

        return names.get(getRandom(names.size()));
    }

    public Task getTask() {
        List<String> names = Arrays.asList("pisanie", "czytanie", "logowanie", "parsowanie", "wyliczanie",
                "wyszukwianie", "odliczanie", "usuwanie", "liczenie", "szacowanie", "rejestracja");
        int tmp = getRandom(names.size());

        return new Task(names.get(tmp), tmp);
    }

    public String getSurname() {
        List<String> surnames = Arrays.asList("Szydlo", "Fred", "Kanar", "Ptaszek",
                "Bialy", "Czarny", "Zegar", "Kwarc", "Felek", "Lisc", "Lodek");

        return surnames.get(getRandom(surnames.size()));
    }

    public String getDomain() {
        List<String> domains = Arrays.asList("gmail.com", "hotmail.com", "mail.com",
                "buziaczek.pl","o2.pl","interia.pl","agh.edu.pl");

        return domains.get(getRandom(domains.size()));
    }

    public String getNationality() {
        List<String> nationalities = Arrays.asList("Poland","Germany","Norway","Czech","Slovakia","Spain","France");

        return nationalities.get(getRandom(nationalities.size()));
    }


    public Sex getSex() {
        return (getRandom(1) == 0)
                ? Sex.MALE
                : Sex.FEMALE;
    }

    public String getAcademy() {
        List<String> academies = Arrays.asList("AGH","SGH","UWR","UJ","UW","PWR","PWSZwKrosnie");

        return academies.get(getRandom(academies.size()));
    }

    public Developer getDeveloper() {
        String name = getName();
        String surname = getSurname();
        String nationality = getNationality();
        String email = name+surname+"@"+getDomain();
        Sex sex = getSex();
        String academy = getAcademy();

        return (Developer)new Developer.Builder(name,surname,email,nationality)
                .sex(sex)
                .academy(academy)
                .build();
    }

    public TeamManager getManager(int maxSize) {
        String name = getName();
        String surname = getSurname();
        String nationality = getNationality();
        String email = name+surname+"@"+getDomain();
        Sex sex = getSex();
        String academy = getAcademy();

        return (TeamManager) new TeamManager.Builder(name,surname,email,maxSize,nationality)
                .sex(sex)
                .academy(academy)
                .build();
    }



    public List<Employee> getDevs(int amount) {
        List<Employee> devs = new ArrayList<>();

        for(int i = 0 ; i < amount ; i++){
            devs.add(getDeveloper());
        }

        return devs;
    }

    public List<Employee> getManagers(int amount, int maxSize) {
        List<Employee> managers = new ArrayList<>();

        for(int i = 0 ; i < amount ; i++){
            managers.add(getManager(maxSize));
        }

        return managers;
    }




}
