CREATE TABLE producto (
      idProducto SMALLINT NOT NULL
    , codigoDeBarras VARCHAR (100) NOT NULL
    , idCategoria SMALLINT NOT NULL
	, nombreProducto VARCHAR(100) NOT NULL
    , idMarca SMALLINT NOT NULL
    , imagenProducto VARCHAR(200) NULL
    , FOREIGN KEY (idCategoria) REFERENCES categoriaProducto(idCategoria)
    , FOREIGN KEY (idMarca) REFERENCES marcaProducto(idMarca)
    , UNIQUE (codigoDeBarras)
	, PRIMARY KEY(idProducto)
)
GO