DROP FUNCTION IF EXISTS date_in_range
GO


CREATE FUNCTION date_in_range(@start DATETIME, @end DATETIME)
RETURNS DATETIME
AS BEGIN
  RETURN (
      SELECT DATEADD(DAY, ABS(CHECKSUM(dbo.get_new_id())) % ( 1 + DATEDIFF(DAY, @start, @end)), @start) AS new_date
  )
END