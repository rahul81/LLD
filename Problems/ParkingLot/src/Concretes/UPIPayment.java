package Concretes;

import interfaces.PaymentStratergy;

public class UPIPayment implements PaymentStratergy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Process payment via UPI");
    }
}
