package Encapsulation.src;

/*
 * Encapsulation is one of the fundamental principles of Object-Oriented Programming (OOP). 
 * It involves bundling data (variables) and methods (functions) that operate on the data into a single unit called a class.
 */

public class Bank {

    private double  balance; // access to variables are restricted and can only be modified by defined methods
    private String accNumber;


    public double getBalance(){
        return balance;
    }

    public String getAccNumber(){
        return accNumber;
    }

    public void deposit(double amount){
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited Amount : " + balance);
        }else{
            System.out.println("Invalid amount : " + amount);
        }
    }

    public void withDraw(double amount){
        if (amount > 0 && balance >= amount){
            balance -= amount;
            System.out.println("Withdrawn amount : " + amount);
        }else{
            System.out.println("Invalid amount : " + amount);
        }
    }

    public static void main(String[] args) {
        Bank b = new Bank();
        b.deposit(1000);
        System.out.println("Current Balance : " + b.getBalance());
        b.withDraw(100);
        System.out.println("Current Balance : " + b.getBalance());
        
    }

}
