CREATE OR REPLACE PROCEDURE ni.NITxIntGradjaninNovaSesija(
    IN  jwtHash text,
    IN  jwtVrednost json,
    IN  trnIstekaJWT timestamp,
    IN  idTipaSesije char,
    IN  idKorisnika uuid,
    IN  idPola text,
    IN  godinaRodjenja integer,
    IN  idOpstine text,
    IN  imePrezime text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
  trnSad timestamp;
  trnIstekaSesije timestamp;
  korisnik RECORD;
  gradjanin RECORD;
BEGIN
    trnSad = now();
    IF idTipaSesije = 'П' THEN
        trnIstekaSesije = trnSad + interval '1 second' * ni.NITxDajNumerickiParametar('ИстекСесијеПотписник');
    ELSE
        trnIstekaSesije = trnSad + interval '1 second' * ni.NITxDajNumerickiParametar('ИстекСесијеИницијатор');
    END IF;
    call ni.NITxIntPodaciOGradjaninu(idKorisnika,idPola, godinaRodjenja, idOpstine, imePrezime, false);
    INSERT INTO ni.NISesija 
      (IDNISesija, IDNITipSesije, IDNIKorisnik, JWT, TrnPocetka, TrnIstekaJWT, TrnIstekaSesije)
    VALUES
      (jwtHash, idTipaSesije, idKorisnika, jwtVrednost, trnSad, trnIstekaJWT, trnIstekaSesije);
END;
$$;
