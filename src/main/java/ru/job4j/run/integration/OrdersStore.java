package ru.job4j.run.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.model.integration.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrdersStore {
    private final BasicDataSource pool;

    public OrdersStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Order save(Order order) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO orders(name, description, created) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, order.getName());
            ps.setString(2, order.getDescription());
            ps.setTimestamp(3, order.getCreated());
            ps.execute();
            ResultSet id = ps.getGeneratedKeys();
            if (id.next()) {
                order.setId(id.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void update(Order order) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE orders SET name = ?, description = ?, created = ? WHERE id = ?")) {
            ps.setString(1, order.getName());
            ps.setString(2, order.getDescription());
            ps.setTimestamp(3, order.getCreated());
            ps.setInt(4, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<Order> findAll() {
        List<Order> list = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM orders")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(
                            new Order(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getTimestamp(4)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Order findById(int id) {
        Order order = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM orders WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Collection<Order> findByName(String name) {
        List<Order> list = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM orders WHERE name = ?")) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(
                            new Order(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getTimestamp(4)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
