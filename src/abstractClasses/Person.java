package abstractClasses;

/**Classe abstrata para aplicação de herança sobre modelos de usuários do sistema StoreCore. @author @MrErykCardoso.*/
public abstract class Person {
    private String name;
    private String cpf;
    private String password;
    private String email;
    private String position;
    private int hierarchy;

    public Person(){}

    public Person(String name, String cpf, String password, String email, String position, int hierarchy){
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.email = email;
        this.position = position;
        this.hierarchy = hierarchy;
    }

    public String getName(){
        return this.name;
    }
    public String getCpf(){
        return this.cpf;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPosition(){
        return this.position;
    }
    public int getHierarchy(){
        return this.hierarchy;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPosition(int hierarchy){
        this.hierarchy = hierarchy;
    }
}
