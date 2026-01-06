package abstract_classes;
import interfaces.Loggable;
import static service.UtilityFunctions.print;

/**Classe abstrata para aplicação de herança e polimorfismo em modelos baseados em produtos da loja gerenciada pelo StoreCore. @author @MrErykCardoso.*/
public abstract class Product implements Loggable{
    private String id;
    private String name;
    private String description;
    private double price;
    private double priceWithDiscount;
    private Enterprise provider;

    public Product(String name, String description, double price, double priceWithDiscount, Enterprise provider){
        this.name = name;
        this.description = description;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.provider = provider;
    }
    public Product(String id, String name, String description, double price, double priceWithDiscount, Enterprise provider){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.provider = provider;
    }

    public String getId(){
        return this.id;
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
    public Enterprise getProvider(){
        return this.provider;
    }

    public void setId(String id){
        this.id = id;
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
    public void setProvider(Enterprise provider){
        this.provider = provider;
    }

    public void printInf(){
        print("---------- Informações do produto: " + this.name + "; ----------");
        print("ID: " + this.id + ";");
        print("DESCRIÇÃO: " + this.description + ";");
        print("PREÇO: " + this.price + ";");
        print("PREÇO COM DESCONTO: " + this.priceWithDiscount + ";");
        print("FORNECEDOR: " + this.provider + ";");
    }
}
