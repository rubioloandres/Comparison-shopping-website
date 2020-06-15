CREATE TABLE categoriaProducto (
	  idCategoria SMALLINT
	, nombreCategoria VARCHAR(50) NOT NULL
	, urlImagenCategoria VARCHAR(100) NOT NULL
	, PRIMARY KEY(idCategoria)
    , UNIQUE (nombreCategoria)
)
GO