CREATE OR REPLACE PROCEDURE ni.NITxDajSesijuPoHash(
    IN  jwtHash Text,
    OUT prisutna boolean,
    OUT isteklaSesija boolean,
    OUT istekaoJWT boolean,
    OUT idTipaSesije char(1),
    OUT idTipaKorisnika char(1)
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
BEGIN
    SELECT *
      INTO sesija
      FROM ni.NISesija s
     WHERE s.IDNISesija = jwtHash;
    prisutna = true;
    isteklaSesija = (sesija.TrnIstekaSesije < now());
    istekaoJWT = (sesija.TrnIstekaJWT < now());
    idTipaSesije = sesija.IDNITipSesije;
    SELECT k.IDNITipKorisnika
      INTO idTipaKorisnika
      FROM ni.NIKorisnik k
     WHERE k.IDNIKorisnik = sesija.IDNIKorisnik;
EXCEPTION
    WHEN no_data_found THEN
        prisutna = false;
        isteklaSesija = null;
        istekaoJWT = null;
        idTipaSesije = null;
        idTipaKorisnika = null;
END;
$$;
