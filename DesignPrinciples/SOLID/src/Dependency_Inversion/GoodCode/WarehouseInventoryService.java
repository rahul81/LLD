package Dependency_Inversion.GoodCode;

import Dependency_Inversion.GoodCode.interfaces.InventoryService;

public class WarehouseInventoryService implements InventoryService {
    
    @Override
    public void updateStock(Order order){

        // update inventory logic 

    }

    @Override
    public boolean checkAvailability(Product product){

        return false;
    }
}
