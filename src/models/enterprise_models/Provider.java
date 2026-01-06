package models.enterprise_models;
import abstract_classes.Enterprise;

public class Provider extends Enterprise{
    public Provider(String name, String cnpj, String email, String phoneNumber){
        super(name, cnpj, email, phoneNumber);
    }
    public Provider(String id, String name, String cnpj, String email, String phoneNumber){
        super(id, name, cnpj, email, phoneNumber);
    }
}
