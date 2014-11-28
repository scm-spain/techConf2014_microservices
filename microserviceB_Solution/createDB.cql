CREATE KEYSPACE MS_SOLUTION_B
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 }
AND DURABLE_WRITES = true;

CREATE TABLE MS_SOLUTION_B.USERS (
  id int PRIMARY KEY,
  email varchar,
  password varchar,
  name varchar,
  surname varchar,
  gender varchar
);


INSERT INTO MS_SOLUTION_B.USERS(ID, EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES(1, 'cayetana@gmail.com', 'password', 'Cayetana', 'Martinez de Irujo', 'F');

INSERT INTO MS_SOLUTION_B.USERS(ID, EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES(2, 'paquirrin@gmail.com', 'password', 'Paquirrin', '', 'M');

INSERT INTO MS_SOLUTION_B.USERS(ID, EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES(3, 'torrente@gmail.com', 'password', 'Torrente', 'El brazo tonto de la ley', 'M');

INSERT INTO MS_SOLUTION_B.USERS(ID, EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES(4, 'email', 'π con todos sus decimales', 'Chuck', 'Norris', 'M-F');

INSERT INTO MS_SOLUTION_B.USERS(ID, EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES(5, 'obi-wan@gmail.com', 'password', 'Obi-Wan', 'Kenobi', 'M');

INSERT INTO MS_SOLUTION_B.USERS(ID, EMAIL, PASSWORD, NAME, SURNAME, GENDER)
VALUES(6, 'pocholo@gmail.com', 'password', 'Pocholo', '', 'M');


