CREATE DATABASE addressBook;

USE addressBook;

CREATE TABLE contacts(
   first_name VARCHAR(255) NOT NULL,
   last_name  VARCHAR(255) NOT NULL,
   gender    VARCHAR(255) NOT NULL,
   number    VARCHAR(255) PRIMARY KEY,
   age       INT NOT NULL,
   email     VARCHAR(255) NOT NULL
);

INSERT INTO contacts(first_name, last_name, gender, number, age, email) VALUES 
("Kathy", "Smith", "F", "0123456789", 18, "kathy.smith@gmail.com"),
("Strong", "Smiley", "M", "04327812", 20, "strong.smiley@gmail.com");

#A few operations
SELECT * FROM contacts;
DROP TABLE contacts;
