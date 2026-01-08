package abstract_classes;
import interfaces.Loggable;
import static service.UtilityFunctions.print;

/**Classe abstrata para aplicação de herança sobre modelos de usuários do sistema StoreCore. @author @MrErykCardoso.*/
public abstract class Person implements Loggable{

    private String id;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;

    public Person(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public Person(String name, String cpf, String email, String phoneNumber){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public Person(String id, String name, String cpf, String email, String phoneNumber){
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getCpf(){
        return this.cpf;
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
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void printInf(){
        print("---------- Informações de " + this.name + "; ----------");
        print("ID: " + this.id + ";");
        print("CPF: " + this.cpf + ";");
        print("EMAIL: " + this.email + ";");
        print("NÚMERO DE TELEFONE: " + this.phoneNumber + ";");
    }
}
