CREATE PROCEDURE SP_GETPRECIOSPRODUCTOS (@idSucursal SMALLINT)
AS
SET NOCOUNT ON
BEGIN
  IF (@idSucursal IS NULL)
    BEGIN
         RAISERROR('El parametro @idSucursal es null', 15, 1)
    END

;WITH T AS
(
  SELECT pp.codigoDeBarras AS codigoDeBarras, MAX(pp.idPrecio) AS idPrecio
  FROM precioProducto pp
  WHERE pp.idSucursal = @idSucursal
  GROUP BY pp.codigoDeBarras,pp.idSucursal
)

SELECT PP.codigoDeBarras, PP.precio,PP.idSucursal
 FROM T
    JOIN precioProducto PP
        ON PP.idPrecio = T.idPrecio
        AND PP.codigoDeBarras = T.codigoDeBarras
 ORDER BY PP.codigoDeBarras
END
GO