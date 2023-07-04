-- liquibase formatted sql

-- changeset liquibase:rseni-code-NIDocListaNeaktivnihInicijativa

CREATE OR REPLACE FUNCTION ni.NIDocListaNeaktivnihInicijativa()
RETURNS json AS
$$
DECLARE
  rezultat JSON;
BEGIN
    SELECT json_agg(a)
      INTO rezultat
      FROM (SELECT i.IDNIInicijativa as "idInicijative", 
                   i.NazivInicijative as  "nazivInicijative", 
                   (SELECT InicijatorovoIme from ni.NIGradjanin s where s.IDNIGradjanin=i.IDNIGradjanin) as "imePrezimeInicijatora",
                   (SELECT Opis from ni.NITipInicijative s where s.IDNITipInicijative=i.IDNITipInicijative) as "tipInicijative",
                   (SELECT Opis from ni.NINivoVlasti s where s.IDNINivoVlasti=i.IDNINivoVlasti) as "nivoVlasti",
                   (SELECT Opis from ni.NIOpstina s where s.IDNIOpstina=i.IDNIOpstina) as "opstina",
                   (SELECT count(*) from ni.NIClanInicijativnogOdbora s where s.IDNIInicijativa=i.IDNIInicijativa) as "brInicijatora",
                   (SELECT Opis from ni.NIFazaObrade s where s.IDNIFazaObrade=i.IDNIFazaObrade) as "fazaObrade",
                   i.DatumAktiviranja as "datumAktiviranja",
                   (SELECT count(*) from ni.NIPotpisInicijative s where s.IDNIInicijativa=i.IDNIInicijativa) as "brPotpisa",
                   i.DatumAktiviranja as "datumAktiviranja",
                   i.Prihvacena as "prihvacena"
              FROM ni.NIINicijativa i
             WHERE i.IDNIFazaObrade IN ('П','К','Н')
             ORDER BY i.DatumAktiviranja, i.IDNIInicijativa
      ) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
