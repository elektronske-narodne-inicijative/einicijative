CREATE OR REPLACE FUNCTION ni.NITxDajParametre()
  RETURNS TABLE (idParametra text, vrednostParametra text)
AS
$$
BEGIN
    RETURN QUERY
    SELECT p.IDNIParametar as idParametra, p.VrednostParametra
      FROM ni.NIParametar p
     WHERE p.niapi;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
