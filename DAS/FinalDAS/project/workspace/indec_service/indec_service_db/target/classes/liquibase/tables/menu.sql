CREATE TABLE menu (
    idMenu SMALLINT NOT NULL,
    nombreMenu VARCHAR (100) NOT NULL,
    dia VARCHAR (15) NOT NULL,
    PRIMARY KEY (idMenu),
    UNIQUE (nombreMenu)
)