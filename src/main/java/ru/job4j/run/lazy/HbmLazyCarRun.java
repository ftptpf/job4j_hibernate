package ru.job4j.run.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.lazy.CarBrandL;
import ru.job4j.model.lazy.CarModelL;

import java.util.ArrayList;
import java.util.List;

public class HbmLazyCarRun {
    public static void main(String[] args) {
        List<CarBrandL> carBrandsListL = new ArrayList<>();
        final StandardServiceRegistry registry  = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            carBrandsListL = session.createQuery("from CarBrandL").list();
            for (CarBrandL brand : carBrandsListL) {
                for (CarModelL model : brand.getModels()) {
                    System.out.println(model);
                }
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
