package models.product_models;
import abstract_classes.Enterprise;
import abstract_classes.StockProduct;

public class HardwareProduct extends StockProduct{
    public HardwareProduct(String name, String description, double price, double priceWithDiscount, Enterprise provider, String code, int inStock){
        super(name, description, price, priceWithDiscount, provider, code, inStock);
    }
    public HardwareProduct(String id, String name, String description, double price, double priceWithDiscount, Enterprise provider, String code, int inStock){
        super(id, name, description, price, priceWithDiscount, provider, code, inStock);
    }
}
