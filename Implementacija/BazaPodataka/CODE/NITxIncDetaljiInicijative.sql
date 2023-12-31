CREATE OR REPLACE PROCEDURE ni.NITxIncDetaljiInicijative(
    IN  jwtHash Text,
    IN  idInicijative integer,
    OUT detaljiInicijative text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    inicijativa RECORD;
BEGIN
    call ni.NITxIntPristupClanaOdbora(jwtHash, idInicijative, sesija, gradjanin, inicijativa);
    SELECT cast (JSONB_STRIP_NULLS(
      JSONB_BUILD_OBJECT(
      'idInicijative',              i.IDNIInicijativa,
      'tipInicijative',             (SELECT Opis from ni.NITipInicijative s where s.IDNITipInicijative=i.IDNITipInicijative),
      'inicijator',                 (SELECT InicijatorovoIme from ni.NIGradjanin s where s.IDNIGradjanin=i.IDNIGradjanin),
      'nazivInicijative',           i.NazivInicijative,
      'tekstInicijative',           i.TekstInicijative,
      'fazaObrade',                 (SELECT Opis from ni.NIFazaObrade s where s.IDNIFazaObrade=i.IDNIFazaObrade),
      'emailZaKontakt',             i.EmailZaKontakt,
      'nivoVlasti',                 (SELECT Opis from ni.NINivoVlasti s where s.IDNINivoVlasti=i.IDNINivoVlasti),
      'jedinicaVlasti',             CASE
                                        WHEN i.IDNINivoVlasti = 'О' THEN (SELECT Opis from ni.NIOpstina s where s.IDNIOpstina=i.IDNIOpstina)
                                        WHEN i.IDNINivoVlasti = 'П' THEN (SELECT Opis from ni.NIPokrajina s where s.IDNIPokrajina=i.IDNIPokrajina)
                                        ELSE 'Србија'
                                    END,
      'trnZahteva',                 i.TrnZahteva,
      'trnOdbijanjaZahteva',        i.TrnOdbijanjaZahteva,
      'razlogOdbijanjaZahteva',     i.RazlogOdbijanjaZahteva,
      'trnPodnosenja',              i.TrnPodnosenja,
      'datumPokretanja',            i.DatumPokretanja,
      'datumOdluke',                i.DatumOdluke,
      'prihvacena',                 CASE
                                        WHEN i.prihvacena = true THEN 'Да'
                                        ELSE 'Не'
                                    END,
      'beleskaSaSednice',           i.BeleskaSaSednice,
      'clanoviInicijativnogOdbora', (
          SELECT JSON_AGG(a)
          FROM (SELECT g.InicijatorovoIme as "imePrezime",
                       g.IDNIPol as "pol",
                       g.GodinaRodjenja as "godinaRodjenja",
                       g.InicijatorovEmail as "emailZaKontakt",
                       g.InicijatorovaBiografija as "biografija"
                  FROM ni.NIClanInicijativnogOdbora c, ni.NIGradjanin g
                 WHERE c.IDNIInicijativa = i.IDNIInicijativa
                   AND c.IDNIGradjanin = g.IDNIGradjanin
                 ORDER BY c.trnPrihvatanjaClanstva
          ) a ),
        'prilozi', (
            SELECT JSON_AGG(b)
              FROM (SELECT p.nazivPriloga as "nazivPriloga",
                           p.urlPriloga as "urlPriloga",
                           p.Sortiranje as "sortiranje"
                      FROM ni.NIPrilogInicijative p
                     WHERE p.IDNIInicijativa = i.IDNIInicijativa
                       AND NOT p.obrisan
                     ORDER BY p.Sortiranje
              ) b ),
        'promene', (
            SELECT JSON_AGG(c)
              FROM (SELECT p.TrnPromene as "TrnPromene",
                           (SELECT Opis from ni.NIFazaObrade s where s.IDNIFazaObrade=p.IDNIFazaObrade) as "fazaObrade",
                           p.DetaljiPromene as "DetaljiPromene"
                      FROM ni.NIDnevnikPromena p
                     WHERE p.IDNIInicijativa = i.IDNIInicijativa
                     ORDER BY p.TrnPromene desc
              ) c )
      )) as text) as detalji
  INTO detaljiInicijative
  FROM ni.NIInicijativa i
 WHERE i.IDNIInicijativa = idInicijative;
END;
$$;
