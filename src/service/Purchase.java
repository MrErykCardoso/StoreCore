package service;
import java.util.List;
import abstract_classes.Collaborator;
import abstract_classes.Product;
import interfaces.Loggable;
import models.person_models.Customer;
import static service.UtilityFunctions.print;

public class Purchase implements Loggable{

    private String id;
    private Customer customer;
    private List<Product> products;
    private Collaborator worker;
    private double totalPrice;
    private String creationDate;// = data atual;

    public Purchase(Customer customer, List<Product> products, Collaborator worker, double totalPrice, String creationDate){
        this.customer = customer;
        this.worker = worker;
        this.totalPrice = sumPrices(products);
        this.creationDate = creationDate;
        this.products = products;
    }
    public Purchase(String id, Customer customer, List<Product> products, Collaborator worker, double totalPrice, String creationDate){
        this.id = id;
        this.customer = customer;
        this.worker = worker;
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
        this.products = products;
    }

    public String getId(){
        return this.id;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public List<Product> getProduct(){
        return this.products;
    }
    public Collaborator getWorker(){
        return this.worker;
    }
    public double getTotalPrice(){
        return this.totalPrice;
    }
    public String getCreationDate(){
        return this.creationDate;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setProduct(List<Product> products){
        this.products = products;
    }
    public void setWorker(Collaborator worker){
        this.worker = worker;
    }
    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }
    public void setCreationDate(String creationDate){
        this.creationDate = creationDate;
    }

    public void printInf(){
        print("---------- Informações de VENDA ----------");
        print("ID: " + this.id + ";");
        print("CLIENTE: " + this.customer + ";");
        print("FUNCIONÁRIO: " + this.worker + ";");
        print("VALOR TOTAL: " + this.totalPrice + ";");
        print("DATA DE CRIAÇÃO: " + this.creationDate + ";");
        print("PRODUTOS:");
        int i = 1;
        for(Product p : this.products){
            print("PRODUTO ["+i+"]: "+p);
            i++;
        }
    }

    public double sumPrices(List<Product> products){
        double total = 0;
        for(Product p : products){
            total += p.getPrice();
        }
        return total; 
    }
}
