CREATE SCHEMA main;

CREATE TABLE main.users
(
    user_id SERIAL,
    user_email TEXT NOT NULL UNIQUE,
    user_password TEXT NOT NULL,
    user_first_name TEXT NOT NULL,
    user_last_name TEXT NOT NULL,
    user_birth_date TIMESTAMP NOT NULL,
    user_token TEXT,
    PRIMARY KEY (user_id)
);