CREATE TABLE localidad (
      idLocalidad SMALLINT IDENTITY NOT NULL
    , nombreLocalidad  VARCHAR (100) NOT NULL
    , idProvincia SMALLINT NOT NULL
    , PRIMARY KEY (idProvincia, idLocalidad)
    , UNIQUE ( idProvincia, nombreLocalidad)
    , FOREIGN KEY (idProvincia) REFERENCES provincia (idProvincia)
)
GO