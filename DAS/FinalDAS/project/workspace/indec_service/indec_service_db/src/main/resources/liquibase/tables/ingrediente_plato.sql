CREATE TABLE ingrediente_plato (
    idIngrediente SMALLINT NOT NULL,
    idPlato SMALLINT NOT NULL,
    descripcion VARCHAR (500) NULL,
    PRIMARY KEY (idIngrediente, idPlato),
    FOREIGN KEY (idIngrediente) REFERENCES ingrediente (idIngrediente),
    FOREIGN KEY (idPlato) REFERENCES plato (idPlato)
)
