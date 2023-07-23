CREATE OR REPLACE FUNCTION ni.NIDocListaAktivnihInicijativa()
RETURNS text AS
$$
DECLARE
  rezultat text;
BEGIN
    SELECT cast(json_agg(a) as text)
      INTO rezultat
      FROM (SELECT i.IDNIInicijativa as "idInicijative", 
                   i.NazivInicijative as  "nazivInicijative", 
                   (SELECT InicijatorovoIme from ni.NIGradjanin s where s.IDNIGradjanin=i.IDNIGradjanin) as "imePrezimeInicijatora",
                   (SELECT Opis from ni.NITipInicijative s where s.IDNITipInicijative=i.IDNITipInicijative) as "tipInicijative",
                   (SELECT Opis from ni.NINivoVlasti s where s.IDNINivoVlasti=i.IDNINivoVlasti) as "nivoVlasti",
                   (SELECT Opis from ni.NIOpstina s where s.IDNIOpstina=i.IDNIOpstina) as "opstina",
                   (SELECT count(*) from ni.NIClanInicijativnogOdbora s where s.IDNIInicijativa=i.IDNIInicijativa) as "brInicijatora",
                   i.DatumAktiviranja as "datumAktiviranja",
                   90 - (current_date - i.DatumAktiviranja) as "brDanaDoZavrsetka",
                   (SELECT count(*) from ni.NIPotpisInicijative s where s.IDNIInicijativa=i.IDNIInicijativa) as "brPotpisa"
              FROM ni.NIINicijativa i
             WHERE i.IDNIFazaObrade = 'А'
             ORDER BY i.DatumAktiviranja, i.IDNIInicijativa
      ) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
