CREATE OR REPLACE PROCEDURE ni.NITxIntDajGradjanina(
    IN  sesija RECORD,
    OUT gradjanin RECORD,
    IN  porukaAkoNepostojeci text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
BEGIN
    SELECT *
      INTO STRICT gradjanin
      FROM ni.NIGradjanin g
     WHERE g.IDNIGradjanin = sesija.IDNIKorisnik;
EXCEPTION
    WHEN no_data_found THEN
        call ni.NITxBaciGresku('NIKNG',sesija.IDNIKorisnik,porukaAkoNepostojeci);
END;
$$;
