package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

/**Classe não instanciável. Funciona apenas paragarantir a construção do banco ded dados antes da inicialização do sistema. @author@MrErykCardoso. */
public final class DatabaseInitializer {

    private DatabaseInitializer(){}

    //Método de inicialização do banco de dados.
    public static void init() {
        String schemaPath = "src/database/schema.sql";

        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement st = conn.createStatement();

            //Lê o arquivo de schema do banco de dados.
            BufferedReader br = new BufferedReader(new FileReader(schemaPath));
            String line;
            String sql = "";

            while ((line = br.readLine()) != null) {
                sql += line + "\n";
            }

            // separa os comandos por ;
            String[] commands = sql.split(";");

            //executa os comenados.
            for (String cmd : commands) {
                if (!cmd.trim().isEmpty()) {
                    st.execute(cmd);
                }
            }

            //Fecha conexão com o database
            br.close();
            st.close();
            conn.close();

            System.out.println("Banco inicializado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao inicializar o banco.");
        }
    }
}
