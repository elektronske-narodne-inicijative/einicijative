CREATE OR REPLACE PROCEDURE ni.NITxOvlOdobriInicijativu(
    IN  jwtHash Text,
    IN  idInicijative integer
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
        call ni.NITxBaciGresku('NIFOA',sesija.IDNIKorisnik,'Ова иницијатива не чека на одобрење!');
    END IF;
    iPosle = iPre;
    iPosle.IDNIFazaObrade = 'А';
    iPosle.DatumAktiviranja = current_date;
    UPDATE ni.NIInicijativa
       SET IDNIFazaObrade         = iPosle.IDNIFazaObrade,
           DatumAktiviranja       = iPosle.DatumAktiviranja
     WHERE IDNIInicijativa        = iPosle.IDNIINicijativa;
    -- ubeleži promenu faze obrade
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash, 'NP', iPre, iPosle);
END;
$$;
