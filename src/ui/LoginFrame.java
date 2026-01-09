package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import abstract_classes.Collaborator;
import service.UserService;

/**Classe de tela de interface de Login. Primeira tela do usuário. @author @MrErykCardoso. */
public class LoginFrame extends JFrame {
    //UI: javax.swing

    private JTextField txtCpf;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    private UserService userService;

    public LoginFrame() {
        this.userService = new UserService();

        setTitle("StoreCore - Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Título
        JLabel lblTitle = new JLabel("StoreCore - Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblTitle, BorderLayout.NORTH);

        // Área central (formulário)
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblCpf = new JLabel("CPF:");
        txtCpf = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();

        formPanel.add(lblCpf);
        formPanel.add(txtCpf);
        formPanel.add(lblSenha);
        formPanel.add(txtSenha);

        panel.add(formPanel, BorderLayout.CENTER);

        // Botão
        btnLogin = new JButton("Entrar");
        panel.add(btnLogin, BorderLayout.SOUTH);

        add(panel);

        // Ação do botão
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
    }

    private void realizarLogin() {
        String cpf = txtCpf.getText();
        String senha = new String(txtSenha.getPassword());

        if (cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha CPF e senha!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Login fake usando UserService
        Collaborator user = userService.login(cpf, senha);

        if (user != null) {
            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + user.getName() + "!",
                    "Login OK",
                    JOptionPane.INFORMATION_MESSAGE);

            // Fecha a tela de login
            dispose();

            // Abre o menu principal
            new MenuFrame(user).setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this,
                    "CPF ou senha inválidos!",
                    "Falha no login",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
