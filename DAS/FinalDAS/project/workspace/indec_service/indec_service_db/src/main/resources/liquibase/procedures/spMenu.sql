CREATE PROCEDURE spMenu
AS
BEGIN
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
    SELECT M.nombreMenu, M.idMenu ,M.dia, P.nombrePlato, P.preparacion, P.idPlato, P.imagenPlato, I.nombreIngrediente, I.idIngrediente, IP.descripcion
        FROM menu M
        JOIN plato P
        ON M.idMenu = P.idMenu
        JOIN ingrediente_plato IP
        ON P.idPlato = IP.idPlato
        JOIN ingrediente I
        ON IP.idIngrediente = I.idIngrediente
    ORDER BY M.idMenu,P.idPlato
END