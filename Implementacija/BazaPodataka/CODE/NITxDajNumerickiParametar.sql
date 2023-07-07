CREATE OR REPLACE FUNCTION ni.NITxDajNumerickiParametar(IN imeParametra text) returns decimal
AS $$
DECLARE
    vrednostParametra decimal;
BEGIN
    SELECT cast(p.VrednostParametra as decimal)
      INTO vrednostParametra
      FROM ni.NIParametar p
     WHERE p.IDNIParametar = imeParametra;
    RETURN vrednostParametra;
END;
$$
LANGUAGE plpgsql SECURITY DEFINER;
