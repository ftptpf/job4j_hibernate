package ru.job4j.model.select;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "s_candidates")
public class CandidateS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_bases_id")
    private BaseS base;

    public static CandidateS of(String name, int age) {
        CandidateS candidate = new CandidateS();
        candidate.setName(name);
        candidate.setAge(age);
        return candidate;
    }
}
