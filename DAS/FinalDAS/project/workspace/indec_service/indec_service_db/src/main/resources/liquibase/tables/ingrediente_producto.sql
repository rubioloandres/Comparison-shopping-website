CREATE TABLE ingrediente_producto (
    idIngrediente SMALLINT NOT NULL,
    idProducto SMALLINT NOT NULL,
    FOREIGN KEY (idIngrediente) REFERENCES ingrediente(idIngrediente),
    FOREIGN KEY (idProducto) REFERENCES producto(idProducto),
    PRIMARY KEY (idIngrediente,idProducto)
)