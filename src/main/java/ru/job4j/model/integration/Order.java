package ru.job4j.model.integration;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {
    private int id;
    private String name;
    private String description;
    private Timestamp created;

    public Order() {
    }

    public Order(int id, String name, String description, Timestamp created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public static Order of(String name, String description) {
        Order order = new Order();
        order.name = name;
        order.description = description;
        order.created = new Timestamp(System.currentTimeMillis());
        return order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id
                && Objects.equals(name, order.name)
                && Objects.equals(description, order.description)
                && Objects.equals(created, order.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}
