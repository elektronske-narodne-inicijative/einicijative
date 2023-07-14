CREATE OR REPLACE PROCEDURE ni.NITxIncDajProfil(
    IN  jwtHash Text,
    OUT idPola text,
    OUT godinaRodjenja integer,
    OUT nazivOpstine text,
    OUT imePrezime text,
    OUT emailAdresa text,
    OUT biografija text
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
    IF sesija.IDNITipSesije != 'И' THEN
        call ni.NITxBaciGresku('NISPT',sesija.IDNIKorisnik,'Сесија неодговарајућег типа за упит профила иницијатора!');
    END IF;
    BEGIN
        SELECT g.IDNIPol, g.GodinaRodjenja, o.Opis, g.InicijatorovoIme, g.InicijatorovEmail, g.InicijatorovaBiografija
          INTO STRICT idPola, godinaRodjenja, nazivOpstine, imePrezime, emailAdresa, biografija
          FROM ni.NIGradjanin g, ni.NIOpstina o
         WHERE g.IDNIGradjanin = sesija.IDNIKorisnik
           and o.IDNIOpstina = g.IDNIOpstina;
    EXCEPTION
        WHEN no_data_found THEN -- ovo se ne očekuje da se dešava (nekonzistentnost podataka u bazi)
            call ni.NITxBaciGresku('NIKNG',sesija.IDNIKorisnik,'Недостају подаци о грађанину за ИД корисника из сесије!');
    END;
END;
$$;
