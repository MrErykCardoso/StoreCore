package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import abstract_classes.Collaborator;
import models.person_models.CEO;
import models.person_models.Manager;
import models.person_models.Customer;
import service.UserService;

public class CustomerFrame extends JFrame {

    private Collaborator loggedUser;
    private UserService userService;

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField txtName, txtCpf, txtEmail, txtPhone;
    private JButton btnAdd, btnEdit, btnRemove, btnBack;

    public CustomerFrame(Collaborator loggedUser) {
        this.loggedUser = loggedUser;
        this.userService = new UserService();

        setTitle("StoreCore - Gerenciar Clientes");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        loadCustomers();
        applyPermissions();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // ===================== TABELA =====================
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Email", "Telefone"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===================== FORMULÁRIO =====================
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        txtName = new JTextField();
        txtCpf = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(txtCpf);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(txtPhone);

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
        btnAdd.addActionListener(e -> addCustomer());
        btnEdit.addActionListener(e -> editCustomer());
        btnRemove.addActionListener(e -> removeCustomer());
        btnBack.addActionListener(e -> {
            dispose();
            new MenuFrame(loggedUser).setVisible(true);
        });
    }

    // ===================== LÓGICA =====================

    private void loadCustomers() {
        tableModel.setRowCount(0);

        List<Customer> customers = userService.listCustomers(loggedUser);
        for (Customer c : customers) {
            tableModel.addRow(new Object[]{
                c.getId(), c.getName(), c.getCpf(), c.getEmail(), c.getPhoneNumber()
            });
        }
    }

    private void addCustomer() {
        try {
            Customer c = new Customer(
                txtName.getText(),
                txtCpf.getText(),
                txtEmail.getText(),
                txtPhone.getText()
            );

            String id = userService.addCustomer(loggedUser, c);
            c.setId(id);
            loadCustomers();
            clearFields();

            JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editCustomer() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela.");
            return;
        }

        try {
            Customer c = new Customer(
                tableModel.getValueAt(row, 0).toString(),
                txtName.getText(),
                txtCpf.getText(),
                txtEmail.getText(),
                txtPhone.getText()
            );

            userService.editCustomer(loggedUser, c);
            loadCustomers();
            clearFields();

            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeCustomer() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela.");
            return;
        }

        String id = tableModel.getValueAt(row, 0).toString();

        try {
            userService.removeCustomer(loggedUser, id);
            loadCustomers();
            clearFields();

            JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }

    private void applyPermissions() {
        boolean canEdit = (loggedUser instanceof CEO) || (loggedUser instanceof Manager);

        btnAdd.setEnabled(canEdit);
        btnEdit.setEnabled(canEdit);
        btnRemove.setEnabled(canEdit);
    }
}
