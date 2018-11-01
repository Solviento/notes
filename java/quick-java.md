// base class (or super class)

public class Employee{

    private String name;

    private int salary;

    

    // constructor

    public Employee(String name, int salary){

        this.name = name;

        this.salary = salary;

    }

    // accessors

    public String getName(){

        return name;

    }

    public int getSalary(){

        return salary;

    }

    // mutator

    public void setSalary(int newSalary){

        salary = newSalary;

    }



}

// child class

public class Manager extends Employee{

    private int bonus;

    public Manager(String name, int salary, int bonus){

        // invoke constructor of super class

        super(name, salary);

        this.bonus = bonus;

    }

    // accessor

    public int getBonus(){

        return bonus;

    }

    // mutator

    public void setBonus(int newBonus){

        bonus = newBonus;

    }

    // override method

    public int getTotalCompensation(){

        return super.getTotalCompensation() + bonus;

    }

}


// abstract class with abstract methods

public abstract class BankAccount{

    protected int balance;

    // accessor

    public int getBalance(){

        return balance;

    }

    public abstract void withdraw(int amount);

}



// child class

public class CheckingAccount extends BankAccount{



    // constructor

    public CheckingAccount(int balance){

        this.balance = balance;

    }

    // mutator

    public void withdraw(int amount){

        balance -= amount;

    }

}



// basic prime checker

for (int I = 2; I < n/2; i++){

    if (n % i == 0) {

        return false;

    }

}

return true;


// interface and implementation


interface MotorVehicle{

    void run();

    int getFuel();

}



class Car implements MotorVehicle{

    int fuel;

    void run(){

        print("wroom");

    }

    int getfuel(){

        return this.fuel;

    }



}


// another abstract class compilation

abstract class MotorVehicle{

int fuel;

int getFuel(){

return this.fuel;

}

abstract void run();

}

class Car extends MotorVehicle{

void run(){

print("wroom");

}

} 
