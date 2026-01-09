package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import abstract_classes.Collaborator;
import models.person_models.CEO;
import models.person_models.Manager;
import models.person_models.Worker;

/**Classe de DAO com funções de manipualção de dados no bando de dados via SQL. @author @MrErykCardoso.*/
public class CollaboratorDAO {
    // Sparação de responsabilidades;
    // Garante persistência no banco de dados;

    public String save(Collaborator c) {
        String sql =
            "INSERT INTO collaborator (name, cpf, email, phone_number, auth, position, hierarchy, wage, role) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getPhoneNumber());
            ps.setString(5, c.getAuth());
            ps.setString(6, c.getPosition());
            ps.setInt(7, c.getHierarchy());
            ps.setDouble(8, c.getWage());
            ps.setString(9, roleOf(c));

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString("id");
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar collaborator.", e);
        }
    }

    public void update(Collaborator c) {
        String sql =
            "UPDATE collaborator SET name=?, cpf=?, email=?, phone_number=?, auth=?, position=?, hierarchy=?, wage=?, role=? " +
            "WHERE id=?::uuid";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getPhoneNumber());
            ps.setString(5, c.getAuth());
            ps.setString(6, c.getPosition());
            ps.setInt(7, c.getHierarchy());
            ps.setDouble(8, c.getWage());
            ps.setString(9, roleOf(c));
            ps.setString(10, c.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar collaborator.", e);
        }
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM collaborator WHERE id = ?::uuid";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover collaborator.", e);
        }
    }

    public Collaborator findById(String id) {
        String sql =
            "SELECT id, name, cpf, email, phone_number, auth, position, hierarchy, wage, role " +
            "FROM collaborator WHERE id = ?::uuid";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar collaborator por id.", e);
        }
    }

    public List<Collaborator> findAll() {
        String sql =
            "SELECT id, name, cpf, email, phone_number, auth, position, hierarchy, wage, role " +
            "FROM collaborator ORDER BY hierarchy DESC, name";

        List<Collaborator> list = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(map(rs));
            return list;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar collaborators.", e);
        }
    }

    // ----------------- helpers -----------------

    private Collaborator map(ResultSet rs) throws Exception {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String cpf = rs.getString("cpf");
        String email = rs.getString("email");
        String phone = rs.getString("phone_number");
        String auth = rs.getString("auth");
        String position = rs.getString("position");
        int hierarchy = rs.getInt("hierarchy");
        double wage = rs.getDouble("wage");
        String role = rs.getString("role");

        if ("CEO".equals(role)) {
            return new CEO(id, name, cpf, email, phone, auth, position, hierarchy, wage);
        }
        if ("MANAGER".equals(role)) {
            return new Manager(id, name, cpf, email, phone, auth, position, hierarchy, wage);
        }
        return new Worker(id, name, cpf, email, phone, auth, position, hierarchy, wage);
    }

    private String roleOf(Collaborator c) {
        if (c instanceof CEO) return "CEO";
        if (c instanceof Manager) return "MANAGER";
        return "WORKER";
    }
}
