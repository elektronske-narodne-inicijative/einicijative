CREATE OR REPLACE PROCEDURE ni.NITxPtpDajProfil(
    IN  jwtHash Text,
    OUT idPola text,
    OUT godinaRodjenja integer,
    OUT nazivOpstine text
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
            call ni.NITxBaciGresku('NISNH',sesija.IDNIKorisnik,'Непознат хеш сесије у преузимању профила корисника!');
    END;
    IF sesija.IDNITipSesije != 'П' THEN
        call ni.NITxBaciGresku('NISPT',sesija.IDNIKorisnik,'Сесија неодговарајућег типа за упит профила потписника!');
    END IF;
    BEGIN
        SELECT g.IDNIPol, g.GodinaRodjenja, o.Opis
          INTO STRICT idPola, godinaRodjenja, nazivOpstine
          FROM ni.NIGradjanin g, ni.NIOpstina o
         WHERE g.IDNIGradjanin = sesija.IDNIKorisnik
           and o.IDNIOpstina = g.IDNIOpstina;
    EXCEPTION
        WHEN no_data_found THEN -- ovo se ne očekuje da se dešava (nekonzistentnost podataka u bazi)
            call ni.NITxBaciGresku('NIKNG',sesija.IDNIKorisnik,'Недостају подаци о грађанину за ИД корисника из сесије!');
    END;
END;
$$;
