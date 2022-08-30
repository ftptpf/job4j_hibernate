package ru.job4j.model.select;

import javax.persistence.*;

@Entity
@Table(name = "s_candidates")
public class CandidateS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
