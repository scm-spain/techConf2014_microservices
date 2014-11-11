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
VALUES('ramonibz@gmail.com', 'password', 'Ramon', 'Rius', 'M');


