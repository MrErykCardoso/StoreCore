package service;

import java.util.List;

import abstract_classes.Collaborator;
import database.CollaboratorDAO;
import database.CustomerDAO;
import models.person_models.CEO;
import models.person_models.Customer;
import models.person_models.Manager;

public class UserService {

    private final CollaboratorDAO collaboratorDAO;
    private final CustomerDAO customerDAO;

    public UserService() {
        this.collaboratorDAO = new CollaboratorDAO();
        this.customerDAO = new CustomerDAO();
    }

    // =========================
    // Permissões (MVP)
    // =========================

    private boolean canManageCollaborators(Collaborator loggedUser) {
        return (loggedUser instanceof CEO);
    }

    private boolean canManageCustomers(Collaborator loggedUser) {
        return (loggedUser instanceof CEO) || (loggedUser instanceof Manager);
    }

    private void require(boolean condition, String message) {
        if (!condition) throw new RuntimeException(message);
    }

    // =========================
    // CRUD - COLLABORATORS
    // (apenas CEO)
    // =========================

    public String addCollaborator(Collaborator loggedUser, Collaborator newCollaborator) {
        require(canManageCollaborators(loggedUser),
            "Acesso negado: apenas CEO pode adicionar colaboradores.");
        return collaboratorDAO.save(newCollaborator);
    }

    public void editCollaborator(Collaborator loggedUser, Collaborator collaborator) {
        require(canManageCollaborators(loggedUser),
            "Acesso negado: apenas CEO pode editar colaboradores.");
        require(collaborator.getId() != null,
            "Collaborator precisa ter ID para editar.");
        collaboratorDAO.update(collaborator);
    }

    public void removeCollaborator(Collaborator loggedUser, String collaboratorId) {
        require(canManageCollaborators(loggedUser),
            "Acesso negado: apenas CEO pode remover colaboradores.");
        collaboratorDAO.deleteById(collaboratorId);
    }

    public List<Collaborator> listCollaborators(Collaborator loggedUser) {
        // MVP: todo mundo pode listar
        return collaboratorDAO.findAll();
    }

    // =========================
    // CRUD - CUSTOMERS
    // (CEO ou Manager)
    // =========================

    public String addCustomer(Collaborator loggedUser, Customer customer) {
        require(canManageCustomers(loggedUser),
            "Acesso negado: apenas CEO ou Manager pode adicionar clientes.");
        return customerDAO.save(customer);
    }

    public void editCustomer(Collaborator loggedUser, Customer customer) {
        require(canManageCustomers(loggedUser),
            "Acesso negado: apenas CEO ou Manager pode editar clientes.");
        require(customer.getId() != null,
            "Customer precisa ter ID para editar.");
        customerDAO.update(customer);
    }

    public void removeCustomer(Collaborator loggedUser, String customerId) {
        require(canManageCustomers(loggedUser),
            "Acesso negado: apenas CEO ou Manager pode remover clientes.");
        customerDAO.deleteById(customerId);
    }

    public List<Customer> listCustomers(Collaborator loggedUser) {
        // MVP: todo mundo pode listar (se quiser restringir, muda aqui)
        return customerDAO.findAll();
    }

    // =========================
    // Login simples (MVP)
    // =========================
    // Autentica por CPF + auth (senha). Retorna o Collaborator logado ou null.
    public Collaborator login(String cpf, String auth) {
        List<Collaborator> list = collaboratorDAO.findAll();

        System.out.println("=== DEBUG LOGIN ===");
        System.out.println("Digitado -> CPF: [" + cpf + "] | SENHA: [" + auth + "]");
        System.out.println("Registros no banco:");

        for (Collaborator c : list) {
            System.out.println("Banco -> CPF: [" + c.getCpf() + "] | SENHA: [" + c.getAuth() + "]");
            if (c.getCpf().trim().equals(cpf.trim()) && c.getAuth().trim().equals(auth.trim())) {
                System.out.println(">>> LOGIN ENCONTRADO <<<");
                return c;
            }
        }

        System.out.println(">>> NENHUM USUÁRIO ENCONTRADO <<<");
        return null;
    }

}
