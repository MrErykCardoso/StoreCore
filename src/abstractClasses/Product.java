package abstractClasses;
import models.Provider;

/**Classe abstrata para aplicação de herança e polimorfismo em modelos baseados em produtos da loja gerenciada pelo StoreCore. @author @MrErykCardoso.*/
public abstract class Product {
    private String name;
    private String description;
    private double price;
    private double priceWithDiscount;
    private String code;
    private Provider provider;
    private int inStock;

    public Product(){}
    public Product(String name, double price, String code, int inStock){
        this.name = name;
        this.price = price;
        this.code = code;
        this.inStock = inStock;
    }
    public Product(String name, String description, double price, double priceWithDiscount, String code, Provider provider, int inStock){
        this.name = name;
        this.description = description;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.code = code;
        this.provider = provider;
        this.inStock = inStock;
    }

    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public double getPrice(){
        return this.price;
    }
    public double getPriceWithDiscount(){
        return this.priceWithDiscount;
    }
    public String getCode(){
        return this.code;
    }
    public Provider getProvider(){
        return this.provider;
    }
    public int getInStock(){
        return this.inStock;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setPriceWithDiscount(double priceWithDiscount){
        this.priceWithDiscount = priceWithDiscount;
    }
    public void setCode(String code){
        this.code = code;
    }
    public void setProvider(Provider provider){
        this.provider = provider;
    }
    public void setInStock(int inStock){
        this.inStock = inStock;
    }
}
