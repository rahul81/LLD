package Concretes;

import interfaces.PaymentStratergy;

public class Payment {

    private double amount;
    private PaymentStratergy paymentStratergy;

    public Payment(double amount, PaymentStratergy paymentStratergy) {
        this.amount = amount;
        this.paymentStratergy = paymentStratergy;
    }

    public void processPayment() {
        if (amount > 0) {
            paymentStratergy.processPayment(amount);
            return;
        }

        System.out.println("Invalid amount : " + amount);
    }
}
