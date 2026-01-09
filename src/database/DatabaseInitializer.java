package database;

import java.io.BufferedReader; //Ler schema.sql
import java.io.FileReader; //Ler schema.sql
import java.sql.Connection; // Abre conexão com banco de dado
import java.sql.Statement;// Executa comandos no banco de dados

/**Classe que não pode ser intanciada e nem herdada. Funciona apenas para garantir a construção do banco ded dados antes da inicialização do sistema. @author@MrErykCardoso. */
public final class DatabaseInitializer {
    //Camada de infraestrutura: Database;

    //Contructor privador e sem método de chamada impede a geração de instâncias;
    private DatabaseInitializer(){}

    //Método de inicialização do banco de dados;
    public static void init() {
        String schemaPath = "src/database/schema.sql";

        try {
            Connection conn = ConnectionFactory.getConnection(); //Usa a classe de conexão para estabelecer contato com o banco de dados;
            Statement st = conn.createStatement(); //Executa comandos no banco de dados usando a conexão

            //Lê o arquivo de schema do banco de dados.
            BufferedReader br = new BufferedReader(new FileReader(schemaPath));
            String line;
            String sql = "";

            //Concatena todas os comando em uma única string
            //Para o laço quando chega no fim do arquivo
            while ((line = br.readLine()) != null) {
                sql += line + "\n";
            }

            // separa os comandos por ;
            String[] commands = sql.split(";");

            //executa os comandos;
            for (String cmd : commands) {
                if (!cmd.trim().isEmpty()) {
                    st.execute(cmd);
                }
            }

            //Fecha conexão com o database e arquivos
            br.close();
            st.close();
            conn.close();

            System.out.println("Banco inicializado com sucesso.");

        } catch (Exception e) {
            //PrintStackTrace ajuda na depuração quando imprime o rastreamento de pilha e exibe a sequência de chamadas de métodos que levou ao erro
            e.printStackTrace();
            System.out.println("Erro ao inicializar o banco.");
        }
    }
}
