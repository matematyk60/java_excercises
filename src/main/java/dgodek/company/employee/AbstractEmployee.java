package dgodek.company.employee;

import dgodek.company.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matematyk60 on 23.07.17.
 */
public abstract class AbstractEmployee implements Employee {
    private final String name;
    private final String surname;
    private final String email;
    private final String nationality;
    private final Sex sex;
    private final String academy;
    final List<Task> tasks;
    private Role role;
    int amountOfWork;

    AbstractEmployee(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.sex = builder.sex;
        this.academy = builder.academy;
        this.nationality = builder.nationality;
        this.role = builder.role;
        this.amountOfWork = 0;
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public String getAcademy() {
        return academy;
    }

    @Override
    public String getNationality() {
        return nationality;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public int getAmountOfWork() {
        return amountOfWork;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AbstractEmployee{" +
                "name='" + name + '\'' +
                ", role=" + role +
                '}';
    }

    public static abstract class Builder<T extends Builder<T>> {
        private final String name;
        private final String surname;
        private final String email;
        private final String nationality;
        private Sex sex;
        private String academy;
        private Role role;

        Builder(String name, String surname, String email, String nationality) {
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.nationality = nationality;
        }

        public T sex(Sex sex) {
            this.sex = sex;

            return (T) this;
        }

        public T academy(String academy) {
            this.academy = academy;
            return (T) this;
        }

        public T role(Role role) {
            this.role = role;
            return (T) this;
        }

        public abstract AbstractEmployee build();
    }
}
