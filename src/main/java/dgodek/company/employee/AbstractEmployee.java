package dgodek.company.employee;

/**
 * Created by matematyk60 on 23.07.17.
 */
public abstract class AbstractEmployee implements Employee {
    private final String name;
    private final String email;
    private final String nationality;
    private Sex sex;
    private String academy;
    private Role role;
    private int amountOfWork;

    public AbstractEmployee(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.sex = builder.sex;
        this.academy = builder.academy;
        this.nationality = builder.nationality;
        this.role = builder.role;
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


    public void setAmountOfWork(int amountOfWork) {
        this.amountOfWork = amountOfWork;
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

    public static abstract class Builder {
        private final String name;
        private final String email;
        private final String nationality;
        private Sex sex;
        private String academy;
        private Role role;

        public Builder(String name, String email, String nationality) {
            this.name = name;
            this.email = email;
            this.nationality = nationality;
        }

        public Builder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public Builder academy(String academy) {
            this.academy = academy;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public abstract AbstractEmployee build();
    }
}
