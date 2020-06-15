CREATE TABLE producto_sucursal (
    idSucursal SMALLINT NOT NULL
   ,codigoDeBarras VARCHAR (100) NOT NULL
   ,activo CHAR NOT NULL
   ,CONSTRAINT rango_activo CHECK (activo IN ('S','N'))
   ,PRIMARY KEY (idSucursal,codigoDeBarras)
   ,FOREIGN KEY (codigoDeBarras) REFERENCES producto (codigoDeBarras)
   ,FOREIGN KEY (idSucursal) REFERENCES sucursal (idSucursal)
 )