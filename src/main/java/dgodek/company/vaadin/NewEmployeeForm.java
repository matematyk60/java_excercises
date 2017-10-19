package dgodek.company.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import dgodek.company.EmployeeFactory;
import dgodek.company.employee.*;

import java.util.Objects;

class NewEmployeeForm extends FormLayout {
    private final Button randomButton = new Button("Get random!");
    private final TextField nameField = new TextField("Name:");
    private final TextField surnameField = new TextField("Surname:");
    private final RadioButtonGroup<Sex> sexRadioButtonGroup = new RadioButtonGroup<>("Sex:");
    private final TextField emailField = new TextField("Email:");
    private final TextField nationalityField = new TextField("Nationality:");
    private final TextField academyField = new TextField("Academy;");
    private final ComboBox<Role> roleBox = new ComboBox<>("Role:");
    private final Button submitButton = new Button("Submit");
    private final ComboBox<TeamManager.HiringStrategy> strategyBox = new ComboBox<>("Strategy");

    private final MainUI ui;

    protected final Binder<EmployeeBuilder> binder = new Binder<>();

    private final EmployeeFactory employeeFactory = new EmployeeFactory();

    NewEmployeeForm(MainUI ui) {
        this.ui = ui;
        this.setSpacing(true);
        this.setMargin(true);
        initFields();
        initListeners();
        initBinder();
        addComponents(randomButton, nameField, surnameField, sexRadioButtonGroup, emailField, nationalityField, academyField, roleBox, strategyBox, submitButton);
    }

    private void initFields() {
        nameField.setWidth("300px");
        surnameField.setWidth("300px");
        emailField.setWidth("300px");
        nationalityField.setWidth("300px");
        academyField.setWidth("300px");
        roleBox.setWidth("300px");
        strategyBox.setWidth("300px");
        sexRadioButtonGroup.setItems(Sex.values());
        roleBox.setItems(Role.values());
        strategyBox.setItems(TeamManager.HiringStrategy.values());
        strategyBox.setVisible(false);
    }

    private void initBinder() {
        binder.forField(nameField)
                .withValidator(t -> t.matches("^[A-ZĄąĆćĘęŁłŃńÓóŚśŹźŻża-z', ]+$"), "Provide correct nameField")
                .bind(EmployeeBuilder::getName, EmployeeBuilder::setName);
        binder.forField(surnameField)
                .withValidator(t -> t.matches("^[A-ZĄąĆćĘęŁłŃńÓóŚśŹźŻża-z', ]+$"), "Provide correct surnameField")
                .bind(EmployeeBuilder::getSurname, EmployeeBuilder::setSurname);
        binder.forField(sexRadioButtonGroup)
                .withValidator(Objects::nonNull, "Provide sex")
                .bind(EmployeeBuilder::getSex, EmployeeBuilder::setSex);
        binder.forField(emailField)
                .withValidator(t -> t.matches("[a-zA-ZĄąĆćĘęŁłŃńÓóŚśŹźŻż0-9._]+@[a-zA-Z0-9.]+\\.[a-z]+"), "Provide correct emailField")
                .bind(EmployeeBuilder::getEmail, EmployeeBuilder::setEmail);
        binder.forField(academyField)
                .withValidator(t -> t.matches("^[A-ZĄąĆćĘęŁłŃńÓóŚśŹźŻża-z', ]+$"), "Provide correct academyField")
                .bind(EmployeeBuilder::getAcademy, EmployeeBuilder::setAcademy);
        binder.forField(nationalityField)
                .withValidator(t -> t.matches("^[A-ZĄąĆćĘęŁłŃńÓóŚśŹźŻża-z', ]+$"), "Provide correct nationalityField")
                .bind(EmployeeBuilder::getNationality, EmployeeBuilder::setNationality);
        binder.forField(roleBox)
                .withValidator(Objects::nonNull, "Provide role")
                .bind(EmployeeBuilder::getRole, EmployeeBuilder::setRole);
        binder.forField(strategyBox)
                .bind(EmployeeBuilder::getStrategy, EmployeeBuilder::setStrategy);
    }

    private void initListeners() {
        randomButton.addClickListener(e -> fillWithRandomEmployee());
        submitButton.addClickListener(e -> {
            EmployeeBuilder builder = new EmployeeBuilder();
            binder.writeBeanIfValid(builder);
            Employee employee = builder.build();
            validateAndExport(employee);
        });
        roleBox.addValueChangeListener(e -> {
            Role role = e.getValue();
            if(isManager(role)) {
                strategyBox.setVisible(true);
            } else {
                strategyBox.setVisible(false);
            }
        });
    }

    protected void validateAndExport(Employee employee) {
        if(isValid(employee)) {
            exportEmployeeAndClose(employee);
        } else {
            Notification.show("Provide correct employee values");
        }
    }

    protected boolean isValid(Employee employee) {
        return binder.isValid();
    }

    private void fillWithRandomEmployee() {
        Employee employee = employeeFactory.getDeveloper();
        nameField.setValue(employee.getName());
        surnameField.setValue(employee.getSurname());
        sexRadioButtonGroup.setSelectedItem(employee.getSex());
        emailField.setValue(employee.getEmail());
        nationalityField.setValue(employee.getNationality());
        academyField.setValue(employee.getAcademy());
        roleBox.setSelectedItem(employee.getRole());
    }


    protected void exportEmployeeAndClose(Employee employee) {
        ui.addEmployee(employee);
        ui.setDefaultContent();
    }

    private static boolean isManager(Role role) {
        return role == Role.CEO ||
                role == Role.MANAGER ||
                role == Role.DEVELOPMENT_MANAGER;
    }

    protected   static class EmployeeBuilder {
        private String name;
        private String surname;
        private Sex sex;
        private String email;
        private String nationality;
        private String academy;
        private Role role;
        private TeamManager.HiringStrategy strategy;

        public Sex getSex() {
            return sex;
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public TeamManager.HiringStrategy getStrategy() {
            return strategy;
        }

        public void setStrategy(TeamManager.HiringStrategy strategy) {
            this.strategy = strategy;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getAcademy() {
            return academy;
        }

        public void setAcademy(String academy) {
            this.academy = academy;
        }

        public Employee build() {
            if(isManager(role)) {
                return buildManager();
            } else {
                return buildDeveloper();
            }
        }

        private Developer buildDeveloper() {
            return new Developer.Builder(name, surname, email,  nationality)
                    .sex(sex)
                    .academy(academy)
                    .role(role)
                    .build();
        }

        private TeamManager buildManager() {
            return new TeamManager.Builder(name, surname, email, 3, nationality)
                    .sex(sex)
                    .academy(academy)
                    .role(role)
                    .hiringStategy(strategy)
                    .build();
        }
    }
}