package dgodek.company.employee;

/**
 * Created by matematyk60 on 23.07.17.
 */
public abstract class AbstractEmployee implements Employee {

    private final String name;

    private String email;

    private Sex sex;

    private String academy;

    private String nationality;

    private Role role;

    private int amountOfWork;

        
    public AbstractEmployee(String name, String email, String nationality) {
        this.name = name;
        this.email = email;
        this.nationality = nationality;
    }

    public AbstractEmployee(String name, String email, Sex sex, String academy, String nationality, Role role) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.academy = academy;
        this.nationality = nationality;
        this.role = role;
        this.amountOfWork = 0;
    }

    public AbstractEmployee(){
        this.name = "def";
    }

    public AbstractEmployee(Builder builder){
        this.name = builder.name;
        this.email = builder.email;
        this.sex = builder.sex;
        this.academy = builder.academy;
        this.nationality = builder.nationality;
        this.role = builder.role;
    }

    public static class Builder {
        private final String name;

        private final String email;

        private final String nationality;

        private Sex sex;

        private String academy;

        private Role role;

        private int maxSize;

        public Builder(String name,String email,String nationality) {
            this.name = name;
            this.email = email;
            this.nationality = nationality;
        }

        public Builder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public Builder maxSize(int maxSize) {
            this.maxSize = maxSize;
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

        public Developer developerBuild() {
            return new Developer(this);
        }

        public TeamManager teamManagerBuild() {
            return new TeamManager(this, maxSize);
        }
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

    public void setRole(Role role) {
        this.role = role;
    }

    public int getAmountOfWork() {
        return amountOfWork;
    }

    public void setAmountOfWork(int amountOfWork) {
        this.amountOfWork = amountOfWork;
    }

    @Override
    public String toString() {
        return "AbstractEmployee{" +
                "name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
