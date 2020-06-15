CREATE PROCEDURE spProvincias
AS
  BEGIN
   SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
   SELECT  codigoEntidadFederal, nombreProvincia
      FROM  provincia
  END
