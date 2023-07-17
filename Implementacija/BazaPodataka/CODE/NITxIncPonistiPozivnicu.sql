CREATE OR REPLACE PROCEDURE ni.NITxIncPonistiPozivnicu(
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
    call ni.NITxIntPristupClanaOdboraZaIzmenu(jwtHash, idInicijative, sesija, gradjanin, inicijativa);
    IF inicijativa.PozivnicaSHA256 is null THEN
        call ni.NITxBaciGresku('NIPBN',sesija.IDNIKorisnik,'Текућа позивница не постоји - не можете је поништити');
    END IF;
    UPDATE ni.NIInicijativa
       SET PozivnicaSHA256 = null
     WHERE IDNIInicijativa = idInicijative;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'ZO', inicijativa, inicijativa);
END;
$$;
