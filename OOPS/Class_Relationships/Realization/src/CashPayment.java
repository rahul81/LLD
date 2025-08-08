package Class_Relationships.Realization.src;

public class CashPayment implements Payment{
    
    @Override
    public void pay() {
        System.out.println("Paid using Cash.");
    }
}
