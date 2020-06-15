CREATE TABLE tecnologia (
	   idTecnologia SMALLINT NOT NULL
      ,nombreTecnologia VARCHAR(20) NOT NULL
	  ,PRIMARY KEY(idTecnologia)
      ,UNIQUE (nombreTecnologia)
)
GO