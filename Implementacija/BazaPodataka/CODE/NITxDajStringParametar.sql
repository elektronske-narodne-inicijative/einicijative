CREATE OR REPLACE FUNCTION ni.NITxDajStringParametar(IN imeParametra text) returns text
AS $$
DECLARE
    vrednostParametra text;
BEGIN
    SELECT p.VrednostParametra
      INTO vrednostParametra
      FROM ni.NIParametar p
     WHERE p.IDNIParametar = imeParametra;
    RETURN vrednostParametra;
END;
$$
LANGUAGE plpgsql SECURITY DEFINER;
