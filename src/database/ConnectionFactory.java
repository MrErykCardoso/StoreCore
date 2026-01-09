package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**Classe de conexão com o database. Cria uma instância de conexão com o database do StoreCore. @author @MrErykCardoso. */
public class ConnectionFactory {

    // Dados de login no database (static final: não muda - constante, não instanciável, não herdável)
    private static final String URL = "jdbc:postgresql://localhost:5432/storecore_db";
    private static final String USER = "storecore_user";
    private static final String PASSWORD = "123";

    // Método de acesso ao banco de dados usando a biblioteca do postgreslq
    public static Connection getConnection() {
        try {
            
            // Aponta para o driver do postgresql (retorns objeto da classe para carregamento específico ~ útil para Drivers)
            Class.forName("org.postgresql.Driver");
            // Retorna conexão com o banco de dados uasando o método java DriveManager para localizar o drive do banco e estabelecer conexão com base na URL, USER e PASSWORD;
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            
            //Classe de erro que detecta a falta do drive para acesso ao banco de dados
            throw new RuntimeException("Driver JDBC do PostgreSQL não encontrado. Coloque o .jar em /lib e reinicie o VS Code.", e);

        } catch (SQLException e) {
            
            //Classe de erro que detecta falha na execução de comando no banco de dados (banco de dados desligado ou dados de acesso incorretos);
            throw new RuntimeException("Falha ao conectar no PostgreSQL. Verifique se o serviço está rodando e se URL/USER/PASSWORD estão corretos.", e);

        }
    }
}
