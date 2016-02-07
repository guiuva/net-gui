/*
 +---------------------------------------------+
 | GUI SHADOW database schema                  |
 +---------------------------------------------+
 | Version  : 0.1                              |
 | Language : MySQL SQL                        |
 | Date     : Wed Jan 4 2016                   |
 | Schema   : shadow/0.1                       |
 | Authors  : David Soler <aensoler@gmail.com> |
 +---------------------------------------------+
*/


-- Database Section
-- ________________

-- create database shadow;


-- Drop Section
-- ____________

DROP TABLE IF EXISTS users_files;
DROP TABLE IF EXISTS users_fields;
DROP TABLE IF EXISTS types;
DROP TABLE IF EXISTS memberships;
DROP TABLE IF EXISTS users_positions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS studies;


-- Creation Section
-- ________________

CREATE TABLE studies (
  id      INTEGER      NOT NULL AUTO_INCREMENT,
  abbr    VARCHAR(10)  NOT NULL,
  literal VARCHAR(100) NOT NULL,
  code    INTEGER,

  PRIMARY KEY (id)
);

CREATE TABLE positions (
  id       INTEGER      NOT NULL AUTO_INCREMENT,
  title    VARCHAR(100) NOT NULL,
  subtitle VARCHAR(100),

  PRIMARY KEY (id)
);

CREATE TABLE users (
  id         VARCHAR(100) NOT NULL,
  password   VARCHAR(100) NOT NULL,
  dni        VARCHAR(10)  NOT NULL,
  nia        VARCHAR(20),
  first_name VARCHAR(100) NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  email      VARCHAR(100) NOT NULL,
  phone      VARCHAR(15),
  ustate     VARCHAR(100),

  PRIMARY KEY (id)
);

CREATE TABLE users_positions (
  user_id      VARCHAR(100) NOT NULL,
  position_id  INTEGER      NOT NULL,
  beginning    DATE         NOT NULL,
  finalisation DATE,

  PRIMARY KEY (user_id, position_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (position_id) REFERENCES positions(id)
);

CREATE TABLE memberships (
  user_id      VARCHAR(100) NOT NULL,
  beginning    DATE         NOT NULL,
  finalisation DATE,

  PRIMARY KEY (user_id, beginning),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE types (
  id       INTEGER      NOT NULL AUTO_INCREMENT,
  literal  VARCHAR(100) NOT NULL,
  category VARCHAR(100),

  PRIMARY KEY (id)
);

CREATE TABLE users_fields (
  id      INTEGER      NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(100) NOT NULL,
  type_id INTEGER      NOT NULL,
  text    VARCHAR(100),

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
);

CREATE TABLE users_files (
  id      INTEGER      NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(100) NOT NULL,
  type_id INTEGER      NOT NULL,
  content MEDIUMBLOB,

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
);


-- Inserts Section
-- _______________

INSERT INTO studies(abbr, literal) VALUES
  ("GII",    "Grado en Ingeniería Informática"),
  ("GII-IS", "Grado en Ingeniería Informática - Mención de Ingeniería de Software"),
  ("GII-TI", "Grado en Ingeniería Informática - Mención en Tecnologías de la Información"),
  ("GII-C",  "Grado en Ingeniería Informática - Mención en Computación"),
  ("GT",     "Grado en Telemática"),
  ("ITIG",   "Ingeniería técnica en Informática de Gestión"),
  ("ITIS",   "Ingeniería técnica en Informática de Sistemas"),
  ("GF",     "Grado en Físicas"),
  ("Indat",  "Grado en Ingeniería Informática y Estadística"),
  ("MII",    "Master en Ingeniería Informática");

INSERT INTO positions(title, subtitle) VALUES
  ("Presidente",     NULL),
  ("Vicepresidente", NULL),
  ("Secretario",     NULL),
  ("Tesorero",       NULL),
  ("Vocal",          NULL),
  ("Vocal", "Administración"),
  ("Vocal", "Exteriores"),
  ("Vocal", "Sede"),
  ("Vocal", "Informática"),
  ("Vocal", "Servicios"),
  ("Vocal", "Desarrollo"),
  ("Vocal", "Actividades");
