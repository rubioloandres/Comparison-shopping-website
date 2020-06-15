CREATE TABLE cadenaServiceConfig (
	  idConfig SMALLINT  NOT NULL
	, idCadena SMALLINT NOT NULL
	, idTecnologia SMALLINT NOT NULL
    , url VARCHAR(200) NOT NULL
	, FOREIGN KEY(idTecnologia) REFERENCES tecnologia(idTecnologia)
	, FOREIGN KEY(idCadena) REFERENCES cadena(idCadena)
	, PRIMARY KEY(idConfig)
)
GO