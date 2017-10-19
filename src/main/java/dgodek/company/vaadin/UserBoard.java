package dgodek.company.vaadin;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import dgodek.company.employee.Employee;

public class UserBoard extends GridLayout {
    private final Label nameLabel = new Label();
    private final Label surnameLabel = new Label();
    private final Label academyLabel = new Label();
    private final Label nationalityLabel = new Label();
    private final Label sexLabel = new Label();
    private final Label emailLabel = new Label();

    public UserBoard() {
        initView();
    }

    public void setValues(Employee employee) {
        nameLabel.setValue(employee.getName());
        surnameLabel.setValue(employee.getSurname());
        academyLabel.setValue(employee.getAcademy());
        nationalityLabel.setValue(employee.getNationality());
        sexLabel.setValue(employee.getSex().toString());
        emailLabel.setValue(employee.getEmail());
    }

    private void initView() {
        VerticalLayout mainLayout = new VerticalLayout();

        HorizontalLayout nameLayout = new HorizontalLayout();
        nameLayout.addComponents(new Label("Name: "), nameLabel);

        HorizontalLayout surnameLayout = new HorizontalLayout();
        surnameLayout.addComponents(new Label("Surname: "), surnameLabel);

        HorizontalLayout academyLayout = new HorizontalLayout();
        academyLayout.addComponents(new Label("Academy: "), academyLabel);

        HorizontalLayout nationalityLayout = new HorizontalLayout();
        nationalityLayout.addComponents(new Label("Nationality: "), nationalityLabel);

        HorizontalLayout sexLayout = new HorizontalLayout();
        sexLayout.addComponents(new Label("Sex: "), sexLabel);

        HorizontalLayout emailLayout = new HorizontalLayout();
        emailLayout.addComponents(new Label("Email: "), emailLabel);
        
        mainLayout.addComponents(nameLayout, surnameLayout, academyLayout,
                nationalityLayout, sexLayout, emailLayout);

        addComponent(mainLayout);
    }
}
