package Concretes;

import interfaces.PaymentStratergy;

public class CreditCardPayment implements PaymentStratergy{
    
    @Override
    public void processPayment(double amount){
        System.out.println("Processing payment via Credit Card");
    }
}
