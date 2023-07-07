CREATE OR REPLACE PROCEDURE ni.NITxPtpNovaSesija(
    IN  jwtHash text,
    IN  jwtVrednost json,
    IN  trnIstekaJWT timestamp,
    IN  idKorisnika uuid,
    IN  idPola text,
    IN  godinaRodjenja integer,
    IN  idOpstine text
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
    trnIstekaSesije = trnSad + interval '1 second' * ni.NITxDajNumerickiParametar('ИстекСесијеПотписник');
    BEGIN
        SELECT *
          INTO STRICT korisnik
          FROM ni.NIKorisnik k
         WHERE k.IDNIKorisnik = idKorisnika;
        IF korisnik.IDNITipKorisnika != 'Г' THEN
           call ni.NITxBaciGresku('NIKNT',idKorisnika,'Идентификатор корисника који је раније био овлашћено лице је сада употребљен за грађанина!');
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
          INTO STRICT gradjanin
          FROM ni.NIGradjanin g
         where g.IDNIGradjanin = idKorisnika;
        IF gradjanin.IDNIPol != idPola OR gradjanin.GodinaRodjenja != godinaRodjenja OR gradjanin.IDNIOpstina != idOpstine THEN
            UPDATE ni.NIGradjanin g 
               SET g.IDNIPol = idPola,
                   g.GodinaRodjenja = godinaRodjenja,
                   g.IDNIOPstina = idOpstine
             WHERE g.IDNIGradjanin = idKorisnika;
        END IF;
    EXCEPTION
        WHEN no_data_found THEN
            INSERT INTO ni.NIGradjanin (IDNIGradjanin, IDNIPol, GodinaRodjenja, IDNIOpstina) VALUES (idKorisnika, idPola, godinaRodjenja, idOpstine);
    END;
    INSERT INTO ni.NISesija 
      (IDNISesija, IDNITipSesije, IDNIKorisnik, JWT, TrnPocetka, TrnIstekaJWT, TrnIstekaSesije)
    VALUES
      (jwtHash, 'П', idKorisnika, jwtVrednost, trnSad, trnIstekaJWT, trnIstekaSesije);
END;
$$;
