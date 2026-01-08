package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import abstract_classes.Collaborator;
import models.person_models.CEO;
import models.person_models.Manager;

public class MenuFrame extends JFrame {

    private Collaborator loggedUser;

    private JButton btnCollaborators;
    private JButton btnCustomers;
    private JButton btnExit;

    public MenuFrame(Collaborator loggedUser) {
        this.loggedUser = loggedUser;

        setTitle("StoreCore - Menu Principal");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // =====================
        // Topo: usuário logado
        // =====================
        JLabel lblUser = new JLabel(
                "Usuário: " + loggedUser.getName() + " (" + loggedUser.getPosition() + ")",
                SwingConstants.CENTER
        );
        lblUser.setFont(new Font("Arial", Font.BOLD, 14));
        lblUser.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lblUser, BorderLayout.NORTH);

        // =====================
        // Centro: botões
        // =====================
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnCollaborators = new JButton("Gerenciar Colaboradores");
        btnCustomers = new JButton("Gerenciar Clientes");
        btnExit = new JButton("Sair");

        buttonsPanel.add(btnCollaborators);
        buttonsPanel.add(btnCustomers);
        buttonsPanel.add(btnExit);

        panel.add(buttonsPanel, BorderLayout.CENTER);

        add(panel);

        // =====================
        // Permissões
        // =====================
        applyPermissions();

        // =====================
        // Ações dos botões
        // =====================
        btnCollaborators.addActionListener(e -> {
            dispose();
            new CollaboratorFrame(loggedUser).setVisible(true);
        });


        btnCustomers.addActionListener(e -> {
            dispose();
            new CustomerFrame(loggedUser).setVisible(true);
        });


        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int op = JOptionPane.showConfirmDialog(
                        MenuFrame.this,
                        "Deseja realmente sair?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );

                if (op == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });
    }

    // =====================
    // Controle de Permissão
    // =====================
    private void applyPermissions() {

        // Apenas CEO pode gerenciar colaboradores
        if (!(loggedUser instanceof CEO)) {
            btnCollaborators.setEnabled(false);
        }

        // CEO e Manager podem gerenciar clientes
        if (!(loggedUser instanceof CEO) && !(loggedUser instanceof Manager)) {
            btnCustomers.setEnabled(false);
        }
    }
}
