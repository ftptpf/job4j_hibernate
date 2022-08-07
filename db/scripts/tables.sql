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

CREATE TABLE IF NOT EXISTS j_role (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(2000)
);

CREATE TABLE IF NOT EXISTS j_user (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(2000) ,
    role_id INT NOT NULL REFERENCES j_role(id)
);

DROP  TABLE j_role;
DROP TABLE j_user;
