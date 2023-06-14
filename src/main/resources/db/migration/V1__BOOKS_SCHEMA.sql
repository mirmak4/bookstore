CREATE TABLE books(
    id NUMERIC(19,0) NOT NULL PRIMARY KEY,
    title VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    release_year INT NOT NULL
)
