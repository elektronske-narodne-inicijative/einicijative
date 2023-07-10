CREATE OR REPLACE PROCEDURE ni.NITxOvlOdbijInicijativu(
    IN  jwtHash Text,
    IN  idInicijative integer,
    IN  obrazlozenje text
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
    IF iPre.IDNIFazaObrade != 'В' THEN
        call ni.NITxBaciGresku('NIFOB',sesija.IDNIKorisnik,'Ова иницијатива не чека на одобрење!');
    END IF;
    iPosle = iPre;
    iPosle.IDNIFazaObrade = 'Б';
    iPosle.TrnOdbijanjaZahteva = current_timestamp;
    iPosle.RazlogOdbijanjaZahteva = obrazlozenje;
    UPDATE ni.NIInicijativa
       SET IDNIFazaObrade         = iPosle.IDNIFazaObrade,
           TrnOdbijanjaZahteva    = iPosle.TrnOdbijanjaZahteva,
           RazlogOdbijanjaZahteva = iPosle.RazlogOdbijanjaZahteva
     WHERE IDNIInicijativa        = iPosle.IDNIINicijativa;
    -- ubeleži promenu faze obrade
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash, 'NP', iPre, iPosle);
END;
$$;
