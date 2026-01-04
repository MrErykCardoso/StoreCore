package models.person_models;
import abstract_classes.Person;

public class Customer extends Person{
    public Customer(String name, String phoneNumber){
        super(name, phoneNumber);
    }
    public Customer(String id, String name, String cpf, String email, String phoneNumber){
        super(id, name, cpf, email, phoneNumber);
    }
}
