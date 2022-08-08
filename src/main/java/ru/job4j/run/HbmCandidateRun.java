package ru.job4j.run;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.model.Candidate;

import java.util.List;

public class HbmCandidateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.of("Ivan", "2 years", 1588);
            Candidate two = Candidate.of("Ilya", "12 years", 78888);
            Candidate three = Candidate.of("Ilya", "1 years", 3238);

            session.persist(one);
            session.persist(two);
            session.persist(three);

            Query query = session.createQuery("from Candidate");
            for (Object candidate : query.list()) {
                System.out.println(candidate);
            }

            Query queryId = session.createQuery("from Candidate c where c.id = :fId");
            queryId.setParameter("fId", 1);
            System.out.println(queryId.getSingleResult());

            Query queryName = session.createQuery("from Candidate c where c.name = :fName");
            queryName.setParameter("fName", "Ilya");
            List candidateList = queryName.list();
            for (Object candidate : candidateList) {
                System.out.println(candidate);
            }

            session.createQuery(
                    "update Candidate c set c.name = :newName, c.experience = :newExperience, c.salary = :newSalary where c.id = :fId")
                    .setParameter("newName", "Ilya")
                    .setParameter("newExperience", "3 years")
                    .setParameter("newSalary", 2588)
                    .setParameter("fId", 1)
                    .executeUpdate();

            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 3)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
