package Dependency_Inversion.GoodCode;

import Dependency_Inversion.GoodCode.interfaces.InventoryService;
import Dependency_Inversion.GoodCode.interfaces.LoggingService;
import Dependency_Inversion.GoodCode.interfaces.NotificationService;

public class OrderService {
    private final NotificationService notificationService;
    private final LoggingService loggingService;
    private final InventoryService inventoryService;
    

    // Not coupled with actual implementations but the abstractions in interface
    // code change can easily happen in each component independantly and objects creared can be passed via constructor.
    public OrderService(
        NotificationService notificationService,
        LoggingService loggingService,
        InventoryService inventoryService
    ){
        this.notificationService = notificationService;
        this.loggingService = loggingService;
        this.inventoryService = inventoryService;
    }

    public void placeOrder(Order order){
        try{
            if (inventoryService.checkAvailability(order.getProduct())){
                //process order 
                inventoryService.updateStock(order);

                // send notification
                notificationService.sendNotification("Order # " + order.getId() + "Order placed");

                // log success
                loggingService.logMessage("Order placed successfully: " + order.getId());

            }
        }
        catch (Exception e){
            loggingService.logError("Error processing order : " + order.getId() + " - " + e.getMessage() );

            throw e;

        }
    }

    public static void main(String[] args) {
        NotificationService emailNotifier = new EmailNotifier();
        LoggingService logger = new DatabaseLogger();
        InventoryService inventory = new WarehouseInventoryService();
        OrderService orderService = new OrderService(emailNotifier, logger, inventory);

        Product product = new Product("Product@Books");
        Order order = new Order(product);
        orderService.placeOrder(order);
    }

}


/*
 * 
 * The Dependency Inversion Principle (DIP) is a principle in object-oriented design that states that High-level modules should not depend on low-level modules. 
 * Both should depend on abstractions. Additionally, abstractions should not depend on details. Details should depend on abstractions.
 * In simpler terms, the DIP suggests that classes should rely on abstractions (e.g., interfaces or abstract classes) rather than concrete implementations. 
 * This allows for more flexible and decoupled code, making it easier to change implementations without affecting other parts of the codebase.
 */