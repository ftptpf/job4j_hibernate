CREATE TABLE IF NOT EXISTS s_bases (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS s_candidates (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) ,
    age INT,
    s_bases_id INT NOT NULL REFERENCES s_bases(id)
);

CREATE TABLE IF NOT EXISTS s_vacancies (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(128) ,
    requirement TEXT ,
    salary INT
);

CREATE TABLE IF NOT EXISTS s_bases_s_vacancies (
    bases_id INT NOT NULL ,
    vacancies_id INT NOT NULL
);

ALTER TABLE s_bases_s_vacancies
    ADD CONSTRAINT bases_id_constraint
    FOREIGN KEY (bases_id) REFERENCES s_bases(id);

ALTER TABLE s_bases_s_vacancies
    ADD CONSTRAINT vacancies_id_constraint
    FOREIGN KEY (vacancies_id) REFERENCES s_vacancies(id);