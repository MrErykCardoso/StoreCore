package models.person_models;
import abstract_classes.Collaborator;
import interfaces.StockOperator;

public class Worker extends Collaborator implements StockOperator{
    public Worker(String name, String cpf, String email, String phoneNumber, String auth, String position, int hierarchy, double wage){
        super(name, cpf, email, phoneNumber, auth, position, hierarchy, wage);
    }
    public Worker(String id, String name, String cpf, String email, String phoneNumber, String auth, String position, int hierarchy, double wage){
        super(id, name, cpf, email, phoneNumber, auth, position, hierarchy, wage);
    }

    //Interface StockOperator
    public boolean canRegisterEntry(){
        return true;
    }
    public boolean canRegisterExit(){
        return true;
    }
}
