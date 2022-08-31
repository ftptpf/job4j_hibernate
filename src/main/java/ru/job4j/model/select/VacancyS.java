package ru.job4j.model.select;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s_vacancies")
public class VacancyS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String requirement;
    private int salary;
}
