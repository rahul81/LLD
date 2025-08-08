package Stratergy.Good;

public class PaymentProcessor {

    private PaymentStratergy paymentStratergy;

    public PaymentProcessor(PaymentStratergy paymentStratergy) {
        this.paymentStratergy = paymentStratergy;
    }

    public void processPayment() {
        paymentStratergy.processPayment();
    }

    public void setPaymentStratergy(PaymentStratergy paymentStratergy) {
        this.paymentStratergy = paymentStratergy;
    }
}
