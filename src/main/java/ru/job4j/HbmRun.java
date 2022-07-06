package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.model.Student;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

/*            Student one = Student.of("Alex", 21, "Moscow");
            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
            Student three = Student.of("Nikita", 25, "Kaliningrad");

            session.persist(one);
            session.persist(two);
            session.persist(three);*/

            Query<Student> query = session.createQuery("from Student ", Student.class);
            for (Object st : query.list()) {
                System.out.println(st);
            }

            Query<Student> studentQuery = session.createQuery("from Student s where s.id = :fId", Student.class);
            studentQuery.setParameter("fId", 2);
            System.out.println(studentQuery.getSingleResult());

            Query<Student> studentQueryUpdate = session.createQuery(
                    "update Student s set s.age = :newAge, s.city = :newCity where s.id = :fId", Student.class);
            studentQueryUpdate.setParameter("newAge", 22);
            studentQueryUpdate.setParameter("newCity", "London");
            studentQueryUpdate.setParameter("fId", 1);
            studentQueryUpdate.executeUpdate();






            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
