CREATE TABLE plato (
    idPlato SMALLINT NOT NULL,
    nombrePlato VARCHAR (100) NOT NULL,
    preparacion VARCHAR (MAX) NOT NULL,
    imagenPlato VARCHAR (100) NULL,
    idMenu SMALLINT NOT NULL,
    PRIMARY KEY (idPlato),
    FOREIGN KEY (idMenu) REFERENCES menu (idMenu)
)
GO