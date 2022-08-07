package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.model.Student;


public class HbmStudentRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Student one = Student.of("Alex", 21, "Moscow");
            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
            Student three = Student.of("Nikita", 25, "Kaliningrad");

            session.save(one);
            session.save(two);
            session.save(three);

            Query query = session.createQuery("from Student");
            for (Object st : query.list()) {
                System.out.println(st);
            }
            Query studentQuery = session.createQuery("from Student s where s.id = :fId");
            studentQuery.setParameter("fId", 2);
            System.out.println(studentQuery.uniqueResult());

            session.createQuery("update Student s set s.age = :newAge, s.city = :newCity where s.id = :fId")
                    .setParameter("newAge", 23)
                    .setParameter("newCity", "Saratov")
                    .setParameter("fId", 1)
                    .executeUpdate();

            session.createQuery("delete from Student where id = :fId")
                    .setParameter("fId", 3)
                    .executeUpdate();

            session.createQuery("insert into Student (name, age, city) "
                    + "select concat(s.name, 'New'), s.age + 5, s.city "
                    + "from Student s where s.id = :fId")
                    .setParameter("fId", 1)
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
