package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import abstract_classes.Collaborator;
import models.person_models.CEO;
import models.person_models.Manager;
import models.person_models.Worker;
import service.UserService;

public class CollaboratorFrame extends JFrame {

    private Collaborator loggedUser;
    private UserService userService;

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField txtName, txtCpf, txtEmail, txtPhone, txtAuth, txtPosition, txtHierarchy, txtWage;
    private JButton btnAdd, btnEdit, btnRemove, btnBack;

    public CollaboratorFrame(Collaborator loggedUser) {
        this.loggedUser = loggedUser;
        this.userService = new UserService();

        setTitle("StoreCore - Gerenciar Colaboradores");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        loadCollaborators();
        applyPermissions();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // ===================== TABELA =====================
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Nome", "CPF", "Email", "Telefone", "Cargo", "Hierarquia", "Salário"}, 0
        );
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===================== FORMULÁRIO =====================
        JPanel formPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Dados do Colaborador"));

        txtName = new JTextField();
        txtCpf = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        txtAuth = new JTextField();
        txtPosition = new JTextField();
        txtHierarchy = new JTextField();
        txtWage = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(txtCpf);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(txtPhone);

        formPanel.add(new JLabel("Senha:"));
        formPanel.add(txtAuth);
        formPanel.add(new JLabel("Cargo:"));
        formPanel.add(txtPosition);

        formPanel.add(new JLabel("Hierarquia:"));
        formPanel.add(txtHierarchy);
        formPanel.add(new JLabel("Salário:"));
        formPanel.add(txtWage);

        add(formPanel, BorderLayout.NORTH);

        // ===================== BOTÕES =====================
        JPanel buttonPanel = new JPanel();

        btnAdd = new JButton("Adicionar");
        btnEdit = new JButton("Editar");
        btnRemove = new JButton("Remover");
        btnBack = new JButton("Voltar");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnBack);

        add(buttonPanel, BorderLayout.SOUTH);

        // ===================== AÇÕES =====================
        btnAdd.addActionListener(e -> addCollaborator());
        btnEdit.addActionListener(e -> editCollaborator());
        btnRemove.addActionListener(e -> removeCollaborator());
        btnBack.addActionListener(e -> {
            dispose();
            new MenuFrame(loggedUser).setVisible(true);
        });
    }

    // ===================== LÓGICA =====================

    private void loadCollaborators() {
        tableModel.setRowCount(0);

        List<Collaborator> collaborators = userService.listCollaborators(loggedUser);
        for (Collaborator c : collaborators) {
            tableModel.addRow(new Object[]{
                c.getId(), c.getName(), c.getCpf(), c.getEmail(),
                c.getPhoneNumber(), c.getPosition(), c.getHierarchy(), c.getWage()
            });
        }
    }

    private void addCollaborator() {
        try {
            Collaborator c = buildCollaborator(null);

            String id = userService.addCollaborator(loggedUser, c);
            c.setId(id);
            loadCollaborators();
            clearFields();

            JOptionPane.showMessageDialog(this, "Colaborador adicionado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editCollaborator() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um colaborador na tabela.");
            return;
        }

        try {
            String id = tableModel.getValueAt(row, 0).toString();
            Collaborator c = buildCollaborator(id);

            userService.editCollaborator(loggedUser, c);
            loadCollaborators();
            clearFields();

            JOptionPane.showMessageDialog(this, "Colaborador atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeCollaborator() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um colaborador na tabela.");
            return;
        }

        String id = tableModel.getValueAt(row, 0).toString();

        try {
            userService.removeCollaborator(loggedUser, id);
            loadCollaborators();
            clearFields();

            JOptionPane.showMessageDialog(this, "Colaborador removido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Collaborator buildCollaborator(String id) {
        String name = txtName.getText();
        String cpf = txtCpf.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String auth = txtAuth.getText();
        String position = txtPosition.getText();
        int hierarchy = Integer.parseInt(txtHierarchy.getText());
        double wage = Double.parseDouble(txtWage.getText());

        // Regra simples:
        // Se hierarquia >= 3 → CEO
        // Se hierarquia == 2 → Manager
        // Senão → Worker
        if (hierarchy >= 3) {
            return (id == null)
                ? new CEO(name, cpf, email, phone, auth, position, hierarchy, wage)
                : new CEO(id, name, cpf, email, phone, auth, position, hierarchy, wage);
        } else if (hierarchy == 2) {
            return (id == null)
                ? new Manager(name, cpf, email, phone, auth, position, hierarchy, wage)
                : new Manager(id, name, cpf, email, phone, auth, position, hierarchy, wage);
        } else {
            return (id == null)
                ? new Worker(name, cpf, email, phone, auth, position, hierarchy, wage)
                : new Worker(id, name, cpf, email, phone, auth, position, hierarchy, wage);
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAuth.setText("");
        txtPosition.setText("");
        txtHierarchy.setText("");
        txtWage.setText("");
    }

    private void applyPermissions() {
        boolean canEdit = (loggedUser instanceof CEO);

        btnAdd.setEnabled(canEdit);
        btnEdit.setEnabled(canEdit);
        btnRemove.setEnabled(canEdit);
    }
}
