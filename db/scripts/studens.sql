CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128),
    age INTEGER,
    city VARCHAR(128)
);

CREATE  TABLE IF NOT EXISTS candidates (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128),
    experience VARCHAR(128),
    salary INT
);

DROP TABLE candidates;