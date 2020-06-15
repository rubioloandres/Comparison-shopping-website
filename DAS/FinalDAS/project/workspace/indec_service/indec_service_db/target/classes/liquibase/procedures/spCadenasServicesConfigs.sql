CREATE PROCEDURE spCadenasServicesConfigs AS
BEGIN
 SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
 SELECT  config.idConfig, cad.idCadena, cad.nombreCadena, tec.nombreTecnologia, config.url
    FROM  cadenaServiceConfig config
    JOIN cadena cad
    ON config.idCadena = cad.idCadena
    JOIN tecnologia tec
    ON  config.idTecnologia = tec.idTecnologia
END
