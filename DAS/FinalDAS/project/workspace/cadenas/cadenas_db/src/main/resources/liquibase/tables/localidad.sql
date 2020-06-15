CREATE TABLE localidad (
     idLocalidad SMALLINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,idProvincia SMALLINT NOT NULL
    ,PRIMARY KEY (idProvincia,idLocalidad)
    ,FOREIGN KEY (idProvincia) REFERENCES provincia (idProvincia)
)