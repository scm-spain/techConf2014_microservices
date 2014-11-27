CREATE KEYSPACE MS_SOLUTION_B
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 }
AND DURABLE_WRITES = true;

CREATE TABLE MS_SOLUTION_B.USERS (
  email varchar PRIMARY KEY,
  password varchar,
  name varchar,
  surname varchar,
  gender varchar
);


INSERT INTO MS_SOLUTION_B.USERS(EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES('cayetana@gmail.com', 'password', 'Cayetana', 'Martinez de Irujo', 'F');

INSERT INTO MS_SOLUTION_B.USERS(EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES('paquirrin@gmail.com', 'password', 'Paquirrin', '', 'M');

INSERT INTO MS_SOLUTION_B.USERS(EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES('torrente@gmail.com', 'password', 'Torrente', 'El brazo tonto de la ley', 'M');

INSERT INTO MS_SOLUTION_B.USERS(EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES('email', 'π con todos sus decimales', 'Chuck', 'Norris', 'M-F');

INSERT INTO MS_SOLUTION_B.USERS(EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES('obi-wan@gmail.com', 'password', 'Obi-Wan', 'Kenobi', 'M');

INSERT INTO MS_SOLUTION_B.USERS(EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES('pocholo@gmail.com', 'password', 'Pocholo', '', 'M');


