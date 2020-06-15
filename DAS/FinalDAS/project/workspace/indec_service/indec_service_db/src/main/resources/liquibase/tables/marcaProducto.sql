CREATE TABLE marcaProducto (
	  idMarca SMALLINT
	, nombreMarca VARCHAR(50) NOT NULL
	, PRIMARY KEY(idMarca)
    , UNIQUE (nombreMarca)
)
GO