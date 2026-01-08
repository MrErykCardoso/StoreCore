import database.DatabaseInitializer;
import database.CollaboratorDAO;
import models.person_models.CEO;
import ui.LoginFrame;

public class App {

    public static void main(String[] args) {

        // 1) Inicializa o banco (cria tabelas se não existirem)
        DatabaseInitializer.init();

        // 2) Garante que exista pelo menos um usuário para login (CEO padrão)
        CollaboratorDAO collaboratorDAO = new CollaboratorDAO();

        if (collaboratorDAO.findAll().isEmpty()) {

            CEO admin = new CEO(
                "MrErykDev",
                "111", // login
                "admin@storecore.com",
                "9999",
                "admin", //senha
                "CEO",
                3,
                10000
            );

            collaboratorDAO.save(admin);

            System.out.println("Usuário padrão criado:");
            System.out.println("CPF: 111");
            System.out.println("Senha: admin");
        }

        // 3) Abre a interface gráfica de login
        new LoginFrame().setVisible(true);
    }
}
