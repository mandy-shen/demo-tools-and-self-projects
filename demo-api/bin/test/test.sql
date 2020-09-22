CREATE TABLE IF NOT EXISTS users
( 
  id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  username varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  status int
);