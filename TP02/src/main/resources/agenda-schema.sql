CREATE TABLE agenda(
  nom VARCHAR(255) PRIMARY KEY
);

CREATE TABLE evenement(
  titre VARCHAR(255),
  debut DATETIME,
  fin DATETIME,
  description TEXT,
  id VARCHAR(255),
  agenda VARCHAR(255) REFERENCES agenda(nom),
  PRIMARY KEY (id,agenda)
);
