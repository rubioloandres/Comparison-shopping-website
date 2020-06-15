CREATE TABLE marcaProducto (
     idMarca SMALLINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,PRIMARY KEY (idMarca)
    ,UNIQUE (nombre)
)
