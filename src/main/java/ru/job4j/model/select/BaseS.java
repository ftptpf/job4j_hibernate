package ru.job4j.model.select;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "s_bases")
public class BaseS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VacancyS> vacancies = new ArrayList<>();

    public void addVacancy(VacancyS vacancy) {
        vacancies.add(vacancy);
    }

    public static BaseS of(String name) {
        BaseS base = new BaseS();
        base.setName(name);
        return base;
    }
}

