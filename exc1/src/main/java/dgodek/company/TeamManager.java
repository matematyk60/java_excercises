package dgodek.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class TeamManager extends AbstactEmployee implements Manager {
    private Employee[] employees;

    private int numberOfEmployees;

    private final int maxSize;

    private int nextWorkerIdx;


    public TeamManager(String name, Role role , int maxSize) {
        super(name, role);
        this.maxSize = maxSize;
        this.nextWorkerIdx = 0;
        this.numberOfEmployees = 0;
        this.employees = new Employee[this.maxSize];
    }

    @Override
    public boolean canHire() {
        return (maxSize > numberOfEmployees);
    }

    @Override
    public void hire(Employee employee) {
        if(canHire()) {
            employees[numberOfEmployees] = employee;
            numberOfEmployees += 1;
        } else{
            throw new TooManyEmployeesException();
        }
    }

    @Override
    public void fire(Employee employee) {
        for(int i = 0 ; i < employees.length ; i++){
            if(employees[i].equals(employee)){
                deleteEmployee(i);
                return;
            }
        }
        throw new NoSuchHiredEmployeeException();
    }

    private void deleteEmployee(int idx){
        employees[idx] = null;
        int i = idx +1;
        while(employees[i] != null && i != maxSize){
            employees[idx] = employees[i];
            i++;
            idx++;
        }
    }

    @Override
    public void assign(Task task) {
        if(nextWorkerIdx == numberOfEmployees){
            nextWorkerIdx = 0;
        }
        System.out.println(this.toString() + " | Assigning " + task.toString() + "to employee " + employees[nextWorkerIdx].toString());
        employees[nextWorkerIdx].assign(task);
        nextWorkerIdx++;
        setAmountOfWork(getAmountOfWork()+task.getunitsOfWork());
    }


    @Override
    public Report reportWork() {
        Report[] subWorkersReports = new Report[maxSize];
        for(int i = 0 ; i < employees.length ; i++){
            subWorkersReports[i] = employees[i].reportWork();
        }
        return new Report(this, subWorkersReports);
    }


    @Override
    public String toString() {
        return super.toString();
    }

}
