CREATE OR REPLACE PROCEDURE ni.NITxIncPovuciInicijativu(
    IN  jwtHash Text,
    IN  idInicijative integer
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    inicijativa RECORD;
BEGIN
    call ni.NITxIntPristupClanaOdbora(jwtHash, idInicijative, sesija, gradjanin, inicijativa);
    IF inicijativa.IDNIFazaObrade != 'А' THEN
        call ni.NITxBaciGresku('NIFIO',sesija.IDNIKorisnik,'Иницијатива није активна - не можете је повући!');
    END IF;
    inicijativa.IDNIFazaObrade = 'О';
    UPDATE ni.NIInicijativa
       SET IDNIFazaObrade = inicijativa.IDNIFazaObrade
     WHERE IDNIInicijativa = idInicijative;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'NP', inicijativa, inicijativa);
END;
$$;
