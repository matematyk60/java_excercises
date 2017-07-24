/**
 * Created by matematyk60 on 23.07.17.
 */
import dgodek.company.*;

import java.util.Random;




public class MainClass{
    private static String getName(){
        String[]names={"Andrzej","Szymon","Piotr","Ambroży",
                "Bonifacy","Bill","Andżelika","Kajtek","Feliks","Beata","Robert"};
        Random generator = new Random();
        int tmp = generator.nextInt(11);
        return names[tmp];
    }

    private static Task getTask(){
        String[] names = {"pisanie", "czytanie", "logowanie", "parsowanie", "wyliczanie",
                "wyszukwianie", "odliczanie", "usuwanie", "liczenie", "szacowanie", "rejestracja"};
        Random generator = new Random();
        int tmp = generator.nextInt(11);
        return new Task(names[tmp],tmp);
    }

    private static Developer[] getDevs(int amount){

        Developer[] devs = new Developer[amount];

        for(int i = 0 ; i < amount ; i++){
            if(i%2==1){
                devs[i] = new Developer(getName(),Role.Tester);
            } else {
                devs[i] = new Developer(getName(),Role.Developer);
            }
        }

        return devs;
    }

    private static Manager[] getManagers(int amount, int size){

        Manager[] managers = new Manager[amount];

        for(int i = 0 ; i < amount ; i++){
            managers[i] = new TeamManager(getName(),Role.Manager, size);
        }

        return managers;
    }

    private static void assignEmployeesToManagers(Manager[] managers, Employee[] employees, int size){
        int actualEmployee = 0;

        for (Manager m : managers) {
            for (int i = 0; i < size; i++) {
                m.Hire(employees[actualEmployee]);
                actualEmployee++;
            }
        }
    }

    public static void main(String[] args){

        Random generator = new Random();
        int depth =5; //generator.nextInt(5)+1;
        int size = Integer.parseInt(args[0]);
        
        Employee[] employees = getDevs((int)Math.pow(size,depth));

        TeamManager ceo = new TeamManager(getName(), Role.CEO, size);
        
        if(depth == 1){
        	for(Employee dev : employees){
        		ceo.Hire(dev);
        	}
        } else {

            Manager[] managers;

            managers = getManagers((int)Math.pow(size, depth-1), size);

            assignEmployeesToManagers(managers,employees,size);

            for (int i = depth - 1; i > 1; i--) {
                employees = managers;
                managers = getManagers((int)Math.pow(size, i-1),size);
                assignEmployeesToManagers(managers,employees,size);
            }

            for (Employee e : managers) {
                ceo.Hire(e);
            }
        }

        for(int i = 0 ; i< 122 ; i++){
            ceo.assign(getTask());
        }

        ceo.reportWork();

    }
}