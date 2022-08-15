package ru.job4j.run;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.manytomany.Author;
import ru.job4j.model.manytomany.Book;

public class HbmManyToManyRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book book1 = Book.of("Dom");
            Book book2 = Book.of("Step");
            Book book3 = Book.of("Summer");

            Author author1 = Author.of("Gogol");
            Author author2 = Author.of("Chehov");
            Author author3 = Author.of("Tolstoy");

            author1.getBooks().add(book1);
            author1.getBooks().add(book2);
            author2.getBooks().add(book3);
            author3.getBooks().add(book3);

            session.persist(author1);
            session.persist(author2);
            session.persist(author3);

            /**
             * Author author = session.get(Author.class, 9);
             * session.remove(author);
             */

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
