CREATE TABLE producto (
     codigoDeBarras VARCHAR (100) NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,idMarca SMALLINT NOT NULL
    ,PRIMARY KEY (codigoDeBarras)
    ,FOREIGN KEY (idMarca) REFERENCES marcaProducto (idMarca)
)