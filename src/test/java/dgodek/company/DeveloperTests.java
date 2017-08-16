package dgodek.company;

import dgodek.company.employee.*;
import dgodek.company.exceptions.CantHireException;
import dgodek.company.report.Report;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeveloperTests {
    private Developer developer;
    private List<Task> tasks;

    @Before
    public void setThings() {
        developer = (Developer) new Developer.Builder("Andrzej","Bysiewicz",
                "matmolub2@gmail.com","Poland").academy("AGH").sex(Sex.MALE).build();
        tasks = Arrays
                .asList(new Task("scripting",2),
                        new Task("comparing",3),
                        new Task("refactoring",4));
    }

    @Test
    public void isAbleToCreateDeveloper() {
        Assert.assertEquals("Andrzej", developer.getName());
        Assert.assertEquals("AGH", developer.getAcademy());
        Assert.assertEquals(0, developer.getAmountOfWork());
        Assert.assertEquals("Bysiewicz", developer.getSurname());
        Assert.assertEquals("matmolub2@gmail.com", developer.getEmail());
        Assert.assertEquals("Poland", developer.getNationality());
        Assert.assertEquals(Role.DEVELOPER, developer.getRole());
        Assert.assertEquals(Sex.MALE, developer.getSex());
    }

    @Test
    public void isAbleToAssignTasks() {
        tasks.forEach((t) -> developer.assign(t));
        Assert.assertArrayEquals(tasks.toArray(),developer.getTasks().toArray());
    }

    @Test
    public void isAbleToReportWork() {
        tasks.forEach((t) -> developer.assign(t));
        Report report = developer.reportWork();
        Assert.assertEquals("WorkerReport{name='Andrzej', surname='Bysiewicz'," +
                " email='matmolub2@gmail.com', nationality='Poland', sex=MALE, academy='AGH'," +
                " role=DEVELOPER, amountOfWork=9, tasks=[Task{name='scripting', unitsOfWork=2}," +
                " Task{name='comparing', unitsOfWork=3}, Task{name='refactoring', unitsOfWork=4}]} ",
                report.toString());
    }


}
