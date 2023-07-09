CREATE OR REPLACE PROCEDURE ni.NITxIntDajInicijativu(
    IN  sesija RECORD,
    IN  idInicijative integer,
    OUT inicijativa RECORD,
    IN  porukaAkoNepostojeca text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
BEGIN
    SELECT *
      INTO STRICT inicijativa
      FROM ni.NIInicijativa i
     WHERE i.IDNIInicijativa = idInicijative;
EXCEPTION
    WHEN no_data_found THEN
        call ni.NITxBaciGresku('NIRNI',sesija.IDNIKorisnik,porukaAkoNepostojeca);
END;
$$;
