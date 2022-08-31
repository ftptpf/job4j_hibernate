package ru.job4j.run.select;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.select.CandidateS;

public class HbmSelectRun {

    public static void main(String[] args) {
        CandidateS candidate = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            candidate = session.createQuery(
                    "SELECT DISTINCT c FROM CandidateS c JOIN FETCH c.base b JOIN FETCH b.vacancies v WHERE c.id = :cId",
                            CandidateS.class)
                    .setParameter("cId", 1)
                    .uniqueResult();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(candidate);
    }
}
