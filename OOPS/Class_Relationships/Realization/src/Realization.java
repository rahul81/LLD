package Class_Relationships.Realization.src;

/*
 * 
 * Realization represents a relationship where a class implements an interface. 
 * It’s like signing a contract to provide specific behaviors
 */

public class Realization {
    public static void main(String[] args) {
        Payment payment1 = new CreditCardPayment();
        Payment payment2 = new CashPayment();
        payment1.pay();
        payment2.pay();
    }
}
