CREATE TABLE provincia (
    idProvincia SMALLINT NOT NULL,
    codigoEntidadFederal VARCHAR (10) NOT NULL,
    nombreProvincia VARCHAR (50) NOT NULL,
    PRIMARY KEY (idProvincia),
    UNIQUE (codigoEntidadFederal),
    UNIQUE (nombreProvincia)
)
GO