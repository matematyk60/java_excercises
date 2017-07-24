package dgodek.company;

/**
 * Created by matematyk60 on 23.07.17.
 */
public class TeamManager extends AbstactEmployee implements Manager {
    private Employee[] employees;

    private int number_of_employees;

    private int max_size;

    private int next_worker;

    private int next_manager;


    public TeamManager(String name, Role role , int max_size) {
        super(name, role);
        this.max_size = max_size;
        this.next_worker = 0;
        this.next_manager = 0;
        this.number_of_employees = 0;
        this.employees = new Employee[this.max_size];
    }

    @Override
    public boolean canHire() {
        return (max_size > number_of_employees);
    }

    @Override
    public void Hire(Employee employee) {
        if(canHire()) {
            employees[number_of_employees] = employee;
            number_of_employees += 1;
        } else{
            throw(new TooManyEmployees());
        }
    }

    @Override
    public void Fire(Employee employee) {
        int i = 0;
        for(Employee tmp : employees){
            if(tmp.getName().equals(employee.getName())){
                deleteEmployee(i);
                break;
            }
            i++;
        }
    }

    private void deleteEmployee(int number){
        employees[number] = null;
        int i = number +1;
        while(employees[i] != null && i != max_size){
            employees[number] = employees[i];
            i++;
            number++;
        }
    }

    @Override
    public void assign(Task task) {
        if(next_worker == number_of_employees){
            next_worker = 0;
        }
        System.out.println(this.toString() + " | Assigning " + task.toString() + "to employee " + employees[next_worker].toString());
        employees[next_worker].assign(task);
        next_worker++;
        setAmountOfWork(getAmountOfWork()+task.getunitsOfWork());
    }


    @Override
    public Report reportWork() {
        for(Employee e : employees){
            e.reportWork();
        }
        return new Report(this);

    }


    @Override
    public String toString() {
        return super.toString();
    }

}
