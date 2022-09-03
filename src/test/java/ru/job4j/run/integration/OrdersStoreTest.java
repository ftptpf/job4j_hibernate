package ru.job4j.run.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.integration.Order;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws Exception {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        File file = Path.of("db", "scripts", "004_ddl_create_table_orders.sql").toFile();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file)))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void deleteTable() throws Exception {
        pool.getConnection().prepareStatement("DROP TABLE orders").execute();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        List<Order> all = (List<Order>) store.findAll();

        assertEquals(1, all.size());
        assertEquals(1, all.get(0).getId());
        assertEquals("description1", all.get(0).getDescription());
    }

    @Test
    public void whenSaveOrderAndUpdateIt() {
        OrdersStore store = new OrdersStore(pool);
        Order order = Order.of("name1", "description1");
        store.save(order);
        Order orderFromBd = store.findById(1);
        order.setId(1);
        assertEquals(order, orderFromBd);

        Order orderUp = Order.of("name2", "description2");
        orderUp.setId(1);
        store.update(orderUp);
        Order orderFromDb2 = store.findById(1);
        assertEquals(1, orderFromDb2.getId());
        assertEquals("name2", orderFromDb2.getName());
        assertEquals("description2", orderFromDb2.getDescription());
    }

    @Test
    public void whenFindAll() {
        OrdersStore store = new OrdersStore(pool);
        Order order1 = Order.of("name1", "description1");
        Order order2 = Order.of("name2", "description2");
        store.save(order1);
        store.save(order2);
        List<Order> all = (List<Order>) store.findAll();

        assertEquals(2, all.size());
        assertEquals("description1", all.get(0).getDescription());
        assertEquals("description2", all.get(1).getDescription());
    }

    @Test
    public void whenFindById() {
        OrdersStore store = new OrdersStore(pool);
        Order order1 = Order.of("name1", "description1");
        Order order2 = Order.of("name2", "description2");
        store.save(order1);
        store.save(order2);
        order1.setId(1);
        Order orderFromDb = store.findById(1);

        assertEquals(order1, orderFromDb);
    }

    @Test
    public void whenFindByName() {
        OrdersStore store = new OrdersStore(pool);
        Order order1 = Order.of("name", "description");
        Order order2 = Order.of("NO-name", "description");
        store.save(order1);
        store.save(order2);
        order1.setId(1);
        Order orderFromDb = store.findByName("name");

        assertEquals(order1, orderFromDb);
    }
}