CREATE TABLE cadena (
	  idCadena SMALLINT NOT NULL
	, nombreCadena VARCHAR(50) NOT NULL
	, imagenCadena VARCHAR(100) NULL
	, PRIMARY KEY(idCadena)
    , UNIQUE (nombreCadena)
)
GO