CREATE OR REPLACE PROCEDURE ni.NITxOvlDajProfil(
    IN  jwtHash Text,
    OUT imePrezime text,
    OUT emailAdresa text,
    OUT nivoUprave text,
    OUT opisJediniceUprave text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
BEGIN
    BEGIN
        SELECT *
          INTO STRICT sesija
          FROM ni.NISesija s
         WHERE s.IDNISesija = jwtHash;
    EXCEPTION
        WHEN no_data_found THEN -- ovo se ne očekuje da se dešava (problem sa transakcijskom obradom?)
            call ni.NITxBaciGresku('NISNH',sesija.IDNIKorisnik,'Непознат хеш сесије у преузимању профила овлашћеног лица!');
    END;
    IF sesija.IDNITipSesije != 'О' THEN
        call ni.NITxBaciGresku('NISPT',sesija.IDNIKorisnik,'Сесија неодговарајућег типа за упит профила овлашћеног лица!');
    END IF;
    BEGIN
        SELECT o.ImeIPrezime, o.EmailAdresa,
               (SELECT n.Opis from ni.NINivoVlasti n where n.IDNINivoVlasti = o.IDNINivoVlasti) as NivoUprave,
               COALESCE (
                   (SELECT p.Opis from ni.NIOpstina p where p.IDNIOpstina = o.IDNIOpstina),
                   (SELECT p.Opis from ni.NIPokrajina p where p.IDNIPokrajina = o.IDNIPokrajina)
               ) as OpisJediniceUprave
          INTO STRICT imePrezime, emailAdresa, nivoUprave, opisJediniceUprave
          FROM ni.NIOvlascenoLice o
         WHERE o.IDNIOvlascenoLice = sesija.IDNIKorisnik;
    EXCEPTION
        WHEN no_data_found THEN -- ovo se ne očekuje da se dešava (nekonzistentnost podataka u bazi)
            call ni.NITxBaciGresku('NIKNO',sesija.IDNIKorisnik,'Недостају подаци о овлашћеном лицу за ИД корисника из сесије!');
    END;
END;
$$;
