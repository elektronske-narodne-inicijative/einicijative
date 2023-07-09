CREATE OR REPLACE PROCEDURE ni.NITxIntDajOpstinu(
    IN  idOpstine text,
    OUT opstina RECORD
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
BEGIN
    SELECT *
      INTO opstina
      FROM ni.NIOpstina o
     WHERE o.IDNIOpstina = idOpstine;
END;
$$;
