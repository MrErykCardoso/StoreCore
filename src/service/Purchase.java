package service;

import abstract_classes.Collaborator;
import abstract_classes.Enterprise;
import abstract_classes.Product;
import interfaces.Loggable;
import models.person_models.Customer;

public class Purchase implements Loggable{

    private String id;
    private Customer customer;
    private Product product[];
    private Collaborator worker;
    private double totalPrice;
    private String creationDate;// = data atual;

    public Purchase(Customer customer, Product product[], Collaborator worker, double totalPrice, String creationDate){
        this.customer = customer;
        this.product = product;
        this.worker = worker;
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
    }
    public Purchase(String id, Customer customer, Product product[], Collaborator worker, double totalPrice, String creationDate){
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.worker = worker;
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
    }

    public String getId(){
        return this.id;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public Product getProduct(){
        return this.product[];
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
    public void setProduct(Product product[]){
        this.product = product[];
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
        //TODO
    }

    public double sumPrices(Product product[]){

        //TODO

        return this.totalPrice; 
    }
}
