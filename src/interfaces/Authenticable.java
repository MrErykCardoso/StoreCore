package interfaces;

/**Interaface de padronização de métodos de autenticação de usuário. @author @MrErykCardoso.*/
public interface Authenticable {
    abstract String getCpf();
    abstract boolean authenticate(String password);
    abstract boolean getHierarchy();
}