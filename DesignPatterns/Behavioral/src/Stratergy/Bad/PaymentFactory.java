package Stratergy.Bad;


/*
 * If needed to add new payment method there will be need to change this switch logic.
 * As we keep adding new payment method it can get tough to maintain hence we use stratergy pattern.
 * 
 */

public class PaymentFactory {
    
    public static void usePaymentMethod(String paymentMEthod){

        switch(paymentMEthod){
            case "CreditCard":
                Payment cc = new CreditCard();
                cc.processPayment();
            case "DebitCard":
                Payment dc = new DebitCard();
                dc.processPayment();
            case "PayPal":
                Payment paypal = new PayPal();
                paypal.processPayment();
            default:
                throw new IllegalArgumentException("Unknown payment method");

        }
    }
}
