package Dependency_Inversion.GoodCode;

public class Order {

    private String orderId;
    private Product product;

    public Order (Product product){
        this.orderId = "Order#" + product.getId();
        this.product = product;

    }
    
    public Product getProduct(){
        return product;
    }


    public String getId(){
        return orderId;
    }
}
