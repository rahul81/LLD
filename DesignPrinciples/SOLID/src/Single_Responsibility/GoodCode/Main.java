package Single_Responsibility.GoodCode;


/*
 * Each class has one responsibility.
 */

public class Main {
    public static void main(String[] args) {
        BreadBaker baker = new BreadBaker();
        InventoryManager manager = new InventoryManager();
        OrderSupplies supplies = new OrderSupplies();

        baker.bakeBread();
        manager.manageInventory();
        supplies.orderSupplies();
    }
}
