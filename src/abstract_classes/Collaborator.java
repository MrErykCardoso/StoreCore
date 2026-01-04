package abstract_classes;

/**Classe abstrata para aplicação de herança sobre modelos de usuários do sistema StoreCore. @author @MrErykCardoso.*/
public abstract class Collaborator extends Person{
    private String auth;
    private String position;
    private int hierarchy;
    private double wage;

    public Collaborator(String name, String cpf, String email, String phoneNumber, String auth, String position, int hierarchy, double wage){
        super(name, cpf, email, phoneNumber);
        this.auth = auth;
        this.position = position;
        this.hierarchy = hierarchy;
        this.wage = wage;
    }
    public Collaborator(String id, String name, String cpf, String auth, String email, String phoneNumber, String position, int hierarchy, double wage){
        super(id, name, cpf, email, phoneNumber);
        this.auth = auth;
        this.position = position;
        this.hierarchy = hierarchy;
        this.wage = wage;
    }

    public String getAuth(){
        return this.auth;
    }
    public String getPosition(){
        return this.position;
    }
    public int getHierarchy(){
        return this.hierarchy;
    }
    public double getWage(){
        return this.wage;
    }

    public void Auth(String auth){
        this.auth = auth;
    }
    public void setPosition(String position){
        this.position = position;
    }
    public void setHierarchy(int hierarchy){
        this.hierarchy = hierarchy;
    }
    public void setWage(double wage){
        this.wage = wage;
    }
}
