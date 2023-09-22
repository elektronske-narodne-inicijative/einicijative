CREATE OR REPLACE PROCEDURE ni.NITxOvlNovaSesija(
    IN  jwtHash text,
    IN  jwtVrednost text,
    IN  trnIstekaJWT timestamp,
    IN  idKorisnika uuid,
    IN  idNivoaVlasti text,
    IN  idOpstine text,
    IN  idPokrajine text,
    IN  imePrezime text,
    IN  emailAdresa text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
  trnSad timestamp;
  trnIstekaSesije timestamp;
  korisnik RECORD;
  ovlice RECORD;
BEGIN
    trnSad = now();
    trnIstekaSesije = trnSad + interval '1 second' * ni.NITxDajNumerickiParametar('ИстекСесијеОвлЛице');
    BEGIN
        SELECT *
          INTO STRICT korisnik
          FROM ni.NIKorisnik k
         WHERE k.IDNIKorisnik = idKorisnika;
        IF korisnik.IDNITipKorisnika != 'О' THEN
           call ni.NITxBaciGresku('NIKNT',idKorisnika,'Идентификатор корисника који је раније био грађанин је сада употребљен за овлашћено лице!');
        END IF;
        UPDATE ni.NIKorisnik
           SET TrnPoslednjeSesije = trnSad
         WHERE IDNIKorisnik = idKorisnika;
    EXCEPTION
        WHEN no_data_found THEN
            INSERT INTO ni.NIKorisnik (IDNIKorisnik, IDNITipKorisnika, TrnKreiranja, TrnPoslednjeSesije) VALUES (idKorisnika,'Г',trnSad,trnSad);
    END;
    BEGIN
        SELECT *
          INTO STRICT ovlice
          FROM ni.NIOvlascenoLice o
         where o.IDNIOvlascenoLice = idKorisnika;
        IF ovlice.IDNINivoVlasti != idNivoaVlasti OR ovlice.IDNIOpstina != idOpstine OR ovlice.IDNIPokrajina != idPokrajine OR ovlice.ImePrezime != imePrezime OR ovlice.EmailAdresa != emailAdresa THEN
            UPDATE ni.NIOvlascenoLice o 
               SET o.IDNINivoVlasti = idNivoaVLasti,
                   o.IDNIOpstina = idOpstine,
                   o.IDNIPokrajina = idPokrajine,
                   o.ImeiPrezime = imePrezime,
                   o.EmailAdresa = emailAdresa
             WHERE o.IDNIOvlascenoLice = idKorisnika;
        END IF;
    EXCEPTION
        WHEN no_data_found THEN
            INSERT INTO ni.NIOvlascenoLice (IDNIOvlascenoLice, IDNINivoVlasti, IDNIOpstina, IDNIPokrajina, ImeiPrezime, EmailAdresa) VALUES (idKorisnika, idNivoaVlasti, idOpstine, idPokrajine, imePrezime, emailAdresa);
    END;
    INSERT INTO ni.NISesija 
      (IDNISesija, IDNITipSesije, IDNIKorisnik, JWT, TrnPocetka, TrnIstekaJWT, TrnIstekaSesije)
    VALUES
      (jwtHash, cast('О' as char), idKorisnika, jwtVrednost, trnSad, trnIstekaJWT, trnIstekaSesije);
END;
$$;
