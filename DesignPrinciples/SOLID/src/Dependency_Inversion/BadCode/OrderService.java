package Dependency_Inversion.BadCode;

public class OrderService {
    private EmailNotifier emailNotifier;
    private DataBaseLogger logger;
    private InventorySystem inventory;


    public OrderService() {
        // Tightly coupled as dependencies with implementation and not abstractions.
        this.emailNotifier = new EmailNotifier();
        this.logger = new DataBaseLogger();
        this.inventory = new InventorySystem();
    }

    public void placeOrder(Order order){
        inventory.updateStock(order);
        emailNotifier.sendEmail("Order " + order.getId() + " placed successfully.");
        logger.logMessage("Order placed "+ order.getId());
    }
}
