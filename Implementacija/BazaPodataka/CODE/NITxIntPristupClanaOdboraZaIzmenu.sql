CREATE OR REPLACE PROCEDURE ni.NITxIntPristupClanaOdboraZaIzmenu(
    IN  jwtHash Text,
    IN  idInicijative integer,
    OUT sesija RECORD,
    OUT gradjanin RECORD,
    OUT inicijativa RECORD
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
   dummy integer;
BEGIN
    call ni.NITxIntPristupClanaOdbora(jwtHash, idInicijative, sesija, gradjanin, inicijativa);
    IF inicijativa.IDNIFazaObrade != 'У' THEN
        call ni.NITxBaciGresku('NIFPR',sesija.IDNIKorisnik,'Иницијатива више није у фази припреме - не можете је мењати!');
    END IF;
END;
$$;
