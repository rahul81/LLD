package Dependency_Inversion.GoodCode.interfaces;

import Dependency_Inversion.GoodCode.Order;
import Dependency_Inversion.GoodCode.Product;

public interface InventoryService {
    void updateStock(Order order);
    boolean checkAvailability(Product product);
}
