CREATE OR REPLACE PROCEDURE ni.NITxOvlRegistrujOdbacenuInicijativu(
    IN  jwtHash text,
    IN  idInicijative integer,
    IN  datumSednice date,
    IN  beleskaSaSednice text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    ovlice RECORD;
    iPre RECORD;
    iPosle RECORD;
BEGIN
    -- učitaj detalje i proveri nadležnost
    call ni.NITxIntInicijaitivaZaOvlAkciju(jwtHash, idInicijative, sesija, ovlice, iPre);
    IF iPre.IDNIFazaObrade != 'П' THEN
        call ni.NITxBaciGresku('NIFOP',sesija.IDNIKorisnik,'Ова иницијатива не чека на унос исхода!');
    END IF;
    iPosle = iPre;
    iPosle.IDNIFazaObrade = 'К';
    iPosle.DatumOdluke = datumSednice;
    iPosle.Prihvacena = false;
    iPosle.BeleskaSaSednice = beleskaSaSednice;
    UPDATE ni.NIInicijativa
       SET IDNIFazaObrade   = iPosle.IDNIFazaObrade,
           DatumOdluke      = iPosle.DatumOdluke,
           Prihvacena       = iPosle.Prihvacena,
           BeleskaSaSednice = iPosle.BeleskaSaSednice
     WHERE IDNIInicijativa  = iPosle.IDNIINicijativa;
    -- ubeleži promenu faze obrade
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash, 'NP', iPre, iPosle);
END;
$$;
