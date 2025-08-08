package Single_Responsibility.BadCode;

/*
 * One class has multiple responsibilties, this does not adhere to Single Responsibility principle.
 * Increases complexity in class if there is a need to change some functionality in methods unrelated to baking bread.
 */

public class BreadBaker {
    
    public void bakeBread(){
        System.out.println("Baking bread...");
    }

    public void manageInventory() {
        System.out.println("Managing Inventory...");
    }

    public void orderSupplies() {
        System.out.println("Ordering supplies...");
    }

    public void serveCustomer() {
        System.out.println("Serving Customer...");
    }

    public static void main(String[] args) {
        
        BreadBaker baker = new BreadBaker();
        baker.bakeBread();
        baker.manageInventory();
        baker.orderSupplies();
        baker.serveCustomer();
    }
}
