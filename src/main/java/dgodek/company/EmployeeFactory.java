package dgodek.company;

import com.google.common.collect.ImmutableList;
import dgodek.company.Task;
import dgodek.company.employee.*;

import java.util.*;

public class EmployeeFactory {
    private Random generator;
    private final ImmutableList<String> names;
    private final ImmutableList<String> surnames;
    private final ImmutableList<String> domains;
    private final ImmutableList<String> nationalities;
    private final ImmutableList<String> academies;


    public EmployeeFactory() {
        this.generator = new Random();
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

    public String getName() {
        return names.get(getRandom(names.size()));
    }

    public String getSurname() {
        return surnames.get(getRandom(surnames.size()));
    }

    public String getDomain() {
        return domains.get(getRandom(domains.size()));
    }

    public String getNationality() {
        return nationalities.get(getRandom(nationalities.size()));
    }

    public Sex getSex() {
        return (getRandom(1) == 0)
                ? Sex.MALE
                : Sex.FEMALE;
    }

    public String getAcademy() {
        return academies.get(getRandom(academies.size()));
    }


    public Developer getDeveloper() {
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

    public TeamManager getManager(int maxSize) {
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

    public List<Developer> getDevs(int amount) {
        List<Developer> devs = new ArrayList<>();
        for(int i = 0 ; i < amount ; i++){
            devs.add(getDeveloper());
        }

        return devs;
    }

    public List<Manager> getManagers(int amount, int maxSize) {
        List<Manager> managers = new ArrayList<>();
        for(int i = 0 ; i < amount ; i++){
            managers.add(getManager(maxSize));
        }

        return managers;
    }

    public static void assignEmployeesToManagers(List<Manager> managers, List<Employee> employees, int size) {
        int actualEmployee = 0;

        for (Manager manager : managers) {
            for (int i = 0; i < size; i++) {
                manager.hire(employees.get(actualEmployee));
                actualEmployee++;
            }
        }
    }
}
