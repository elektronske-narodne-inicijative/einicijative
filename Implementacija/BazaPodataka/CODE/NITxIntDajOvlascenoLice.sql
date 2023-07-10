CREATE OR REPLACE PROCEDURE ni.NITxIntDajOvlascenoLice(
    IN  sesija RECORD,
    OUT ovlice RECORD,
    IN  porukaAkoNepostojeci text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
BEGIN
    SELECT *
      INTO STRICT ovlice
      FROM ni.NIOvlascenoLice g
     WHERE g.IDNIOvlascenoLice = sesija.IDNIKorisnik;
EXCEPTION
    WHEN no_data_found THEN
        call ni.NITxBaciGresku('NIKNO',sesija.IDNIKorisnik,porukaAkoNepostojeci);
END;
$$;
