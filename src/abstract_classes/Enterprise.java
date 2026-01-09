package abstract_classes;
import static service.UtilityFunctions.print;

import interfaces.Loggable;

/**Classe abstrata para  */
public abstract class Enterprise implements Loggable{
    private String id;
    private String name;
    private String cnpj;
    private String email;
    private String phoneNumber;

    public Enterprise(String name, String cnpj, String email, String phoneNumber){
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public Enterprise(String id, String name, String cnpj, String email, String phoneNumber){
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getCnpj(){
        return this.cnpj;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void sePhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void printInf(){
        print("---------- Informações da empresa: " + this.name + "; ----------");
        print("ID: " + this.id + ";");
        print("CNPJ: " + this.cnpj + ";");
        print("EMAIL: " + this.email + ";");
        print("NÚMERO DE TELEFONE: " + this.phoneNumber + ";");
    }
}
