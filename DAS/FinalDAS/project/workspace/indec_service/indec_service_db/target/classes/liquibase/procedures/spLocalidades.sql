CREATE PROCEDURE spLocalidades AS
BEGIN
     SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
     SELECT codigoEntidadFederal, nombreLocalidad
      FROM localidad l
      JOIN provincia p
      ON l.idProvincia = p.idProvincia

END
