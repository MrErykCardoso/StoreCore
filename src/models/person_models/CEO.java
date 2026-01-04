package models.person_models;
import abstract_classes.Collaborator;
import interfaces.*;
public class CEO extends Collaborator implements Authenticable, StockManager, StockOperator{

    public CEO(String name, String cpf, String email, String phoneNumber, String auth, String position, int hierarchy, double wage){
        super(name, cpf, email, phoneNumber, auth, position, hierarchy, wage);
    }
    public CEO(String id, String name, String cpf, String email, String phoneNumber, String auth, String position, int hierarchy, double wage){
        super(id, name, cpf, email, phoneNumber, auth, position, hierarchy, wage);
    }
    
    //Interface Authenticable
    public boolean authenticate(String auth){
        return this.getAuth().equals(auth);
    }

    //Interface StockManager
    public boolean canRegisterProduct(){
        return true;
    }
    public boolean canEditProduct(){
        return true;
    }
    public boolean canDeleteProduct(){
        return true;
    }

    //Interface StockOperator
    public boolean canRegisterEntry(){
        return true;
    }
    public boolean canRegisterExit(){
        return true;
    }
}
