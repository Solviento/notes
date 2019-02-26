# Quick Java Code Reference

Parent Class
``` Java
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
```

Child Class
``` Java
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
```

Abstract Class
``` Java
// abstract class with abstract methods
public abstract class BankAccount{
    protected int balance;
    // accessor
    public int getBalance(){
        return balance;
    }
    abstract void withdraw(int amount);
}

class Checking extends BankAccount{
    void withdraw(int amount){
        this.balance -= amount;
    }
}
```

Prime Checker using Divisibility by 2
``` Java
// basic prime checker
for (int i = 2; i < n/2; i++){
    if (n % i == 0) {
        return false;
    }
}
return true;
```

Interface Implementation
``` Java
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
```
