CREATE TABLE IF NOT EXISTS s_candidates (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) ,
    experience TEXT
);

CREATE TABLE IF NOT EXISTS s_base (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS s_vacancy (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(128) ,
    salary INT
);