INSERT INTO s_bases(name) VALUES ('one');
INSERT INTO s_bases(name) VALUES ('two');
INSERT INTO s_bases(name) VALUES ('three');

INSERT INTO s_candidates(name, age, s_bases_id) VALUES ('Ivan', 23, 1);
INSERT INTO s_candidates(name, age, s_bases_id) VALUES ('Sergey', 30, 2);
INSERT INTO s_candidates(name, age, s_bases_id) VALUES ('Igor', 40, 3);

INSERT INTO s_vacancies(name, requirement, salary) VALUES ('Java', 'SQL, JAVA', 1500);
INSERT INTO s_vacancies(name, requirement, salary) VALUES ('Java', 'SQL, JAVA, Scala, Spring', 2500);
INSERT INTO s_vacancies(name, requirement, salary) VALUES ('QA', 'SQL, JS', 1500);
INSERT INTO s_vacancies(name, requirement, salary) VALUES ('JS', 'Html, SQL, CSS, JS', 2000);
INSERT INTO s_vacancies(name, requirement, salary) VALUES ('PM', 'SQL, JAVA', 2000);


INSERT INTO s_bases_s_vacancies(bases_id, vacancies_id) VALUES (1, 1);
INSERT INTO s_bases_s_vacancies(bases_id, vacancies_id) VALUES (1, 4);
INSERT INTO s_bases_s_vacancies(bases_id, vacancies_id) VALUES (2, 2);
INSERT INTO s_bases_s_vacancies(bases_id, vacancies_id) VALUES (2, 1);
INSERT INTO s_bases_s_vacancies(bases_id, vacancies_id) VALUES (3, 5);
INSERT INTO s_bases_s_vacancies(bases_id, vacancies_id) VALUES (3, 3);
INSERT INTO s_bases_s_vacancies(bases_id, vacancies_id) VALUES (3, 1);