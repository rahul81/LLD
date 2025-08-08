package Dependency_Inversion.GoodCode;

public class Product {

    private String productId;

    Product(String Id){
        this.productId = Id;
    }

    public String getId(){
        return productId;
    }
    
}
