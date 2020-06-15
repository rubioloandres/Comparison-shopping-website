CREATE PROCEDURE spProductos  AS
 BEGIN
          SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
          SELECT p.codigoDeBarras, p.idCategoria, c.nombreCategoria, p.nombreProducto, m.nombreMarca, p.imagenProducto
             FROM producto p
             JOIN categoriaProducto c
             ON p.idCategoria = c.idCategoria
             JOIN marcaProducto m
             ON p.idMarca = m.idMarca
END