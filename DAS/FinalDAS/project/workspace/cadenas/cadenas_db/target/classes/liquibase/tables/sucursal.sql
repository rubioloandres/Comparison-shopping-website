CREATE TABLE sucursal (
     idSucursal SMALLINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,cuit VARCHAR(20) NOT NULL
    ,idProvincia SMALLINT NOT NULL
    ,idLocalidad SMALLINT NOT NULL
    ,direccion VARCHAR (100) NOT NULL
    ,telefono VARCHAR (20) NOT NULL
    ,email VARCHAR (100) NOT NULL
    ,lat VARCHAR (30) NOT NULL
    ,lng VARCHAR (30) NOT NULL
    ,PRIMARY KEY (idSucursal)
    ,FOREIGN KEY (idProvincia,idLocalidad) REFERENCES localidad (idProvincia,idLocalidad)
)