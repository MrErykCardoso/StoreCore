package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.person_models.Customer;

public class CustomerDAO {

    public String save(Customer customer) {
        String sql = "INSERT INTO customer (name, cpf, email, phone_number) VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getCpf());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString("id");
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar customer.", e);
        }
    }

    public void update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, cpf = ?, email = ?, phone_number = ? WHERE id = ?::uuid";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getCpf());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar customer.", e);
        }
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM customer WHERE id = ?::uuid";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover customer.", e);
        }
    }

    public Customer findById(String id) {
        String sql = "SELECT id, name, cpf, email, phone_number FROM customer WHERE id = ?::uuid";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar customer por id.", e);
        }
    }

    public List<Customer> findAll() {
        String sql = "SELECT id, name, cpf, email, phone_number FROM customer ORDER BY name";
        List<Customer> list = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(map(rs));
            return list;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar customers.", e);
        }
    }

    private Customer map(ResultSet rs) throws Exception {
        return new Customer(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("cpf"),
            rs.getString("email"),
            rs.getString("phone_number")
        );
    }
}
