CREATE TABLE ingrediente (
    idIngrediente SMALLINT NOT NULL,
    nombreIngrediente VARCHAR (100) NOT NULL,
    PRIMARY KEY (idIngrediente),
    UNIQUE (nombreIngrediente)
)