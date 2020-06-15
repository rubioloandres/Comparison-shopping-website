CREATE PROCEDURE SP_INSERTPRECIOPRODUCTO (@idSucursal SMALLINT, @precio REAL ,@codigoDeBarras VARCHAR(100))
AS
SET NOCOUNT ON
BEGIN
  IF (@idSucursal IS NULL)
    BEGIN
         RAISERROR('El parametro @idSucursal es null', 15, 1)
    END

  IF (@precio IS NULL)
    BEGIN
         RAISERROR('El parametro @precio es null', 15, 1)
    END

  IF (@codigoDeBarras IS NULL)
    BEGIN
         RAISERROR('El parametro @codigoDeBarras es null', 15, 1)
    END

  INSERT INTO precioProducto (precio , idSucursal, codigoDeBarras) VALUES (@precio ,@idSucursal ,@codigoDeBarras)

END
GO