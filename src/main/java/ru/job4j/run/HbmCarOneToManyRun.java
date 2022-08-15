package ru.job4j.run;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.onetomany.CarBrand;
import ru.job4j.model.onetomany.CarModel;

public class HbmCarOneToManyRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            CarModel zx = CarModel.of("zx14");
            CarModel xt = CarModel.of("xt22");
            CarModel tr = CarModel.of("tr9009");
            CarModel cv = CarModel.of("cv33");
            CarModel lt = CarModel.of("lt10");
            session.save(zx);
            session.save(xt);
            session.save(tr);
            session.save(cv);
            session.save(lt);

            CarBrand brand = CarBrand.of("VW");
            brand.addCarModel(session.load(CarModel.class, 1));
            brand.addCarModel(session.load(CarModel.class, 2));
            brand.addCarModel(session.load(CarModel.class, 3));
            brand.addCarModel(session.load(CarModel.class, 4));
            brand.addCarModel(session.load(CarModel.class, 5));

            session.save(brand);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
