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

CREATE TABLE IF NOT EXISTS cars (
    id SERIAL PRIMARY KEY,
    model VARCHAR(128),
    created TIMESTAMP
);

DROP TABLE cars;