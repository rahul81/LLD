package Stratergy.Good;


/*
 * Can switch payment method objects at runtime.
 * When new payment method classes are added 
 * there is no need to make change in PaymentProcessor class code. 
 * 
 */

public class Main {

    public static void main(String[] args) {
        
        CreditCard creditCard = new CreditCard();
        DebitCard debitCard = new DebitCard();
        PayPal payPal = new PayPal();

        PaymentProcessor paymentProcessor = new PaymentProcessor(payPal);

        paymentProcessor.processPayment();

        paymentProcessor.setPaymentStratergy(creditCard);
        paymentProcessor.processPayment();

        paymentProcessor.setPaymentStratergy(debitCard);
        paymentProcessor.processPayment();
    }
    
}
