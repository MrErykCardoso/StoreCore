package models.product_models;

import abstract_classes.Enterprise;
import abstract_classes.Service;;

public class Repair extends Service{
    public Repair(String name, String description, double price, double priceWithDiscount, Enterprise provider, String startDate, String endDate){
        super(name, description, price, priceWithDiscount, provider, startDate, endDate);
    }
    public Repair(String id, String name, String description, double price, double priceWithDiscount, Enterprise provider, String startDate, String endDate){
        super(id, name, description, price, priceWithDiscount, provider, startDate, endDate);
    }
}
