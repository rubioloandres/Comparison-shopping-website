CREATE PROCEDURE spCategoriasProducto AS
BEGIN
 SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
 SELECT idCategoria, nombreCategoria ,urlImagenCategoria
    FROM categoriaProducto
END
