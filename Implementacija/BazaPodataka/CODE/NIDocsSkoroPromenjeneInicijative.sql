CREATE OR REPLACE FUNCTION ni.NIDocsSkoroPromenjeneInicijative()
  RETURNS TABLE (idInicijative INTEGER, jsonDokument text)
AS
$$
DECLARE
    brojPoslednjihSekundi INT;
BEGIN
    brojPoslednjihSekundi = ni.NITxDajNumerickiParametar('ПериодИзменаЗаДетаље');
    RETURN QUERY
SELECT i.IDNIINicijativa as idInicijative, cast (JSONB_STRIP_NULLS(
  JSONB_BUILD_OBJECT(
  'idInicijative',              i.IDNIInicijativa,
  'tipInicijative',             (SELECT Opis from ni.NITipInicijative s where s.IDNITipInicijative=i.IDNITipInicijative),
  'imePrezimeInicijatora',      (SELECT InicijatorovoIme from ni.NIGradjanin s where s.IDNIGradjanin=i.IDNIGradjanin),
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
  'datumAktiviranja',           i.DatumAktiviranja,
  'datumPokretanja',            i.DatumPokretanja,
  'datumOdluke',                i.DatumOdluke,
  'prihvacena',                 CASE
                                    WHEN i.prihvacena = true THEN 'Да'
                                    ELSE 'Не'
                                END,
  'beleskaSaSednice',           i.BeleskaSaSednice,
  'datumOdluke',                i.DatumOdluke,
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
  'potpisiGeografija', (
      SELECT JSON_AGG(c)
      FROM (SELECT g.IDNIOpstina as "idOpstine",
                   count(*) as "brojPotpisa"
              FROM ni.NIPotpisInicijative p, ni.NIGradjanin g
             WHERE p.IDNIInicijativa = i.IDNIInicijativa
               AND p.IDNIGradjanin = g.IDNIGradjanin
             GROUP BY g.IDNIOpstina
             ORDER BY g.IDNIOpstina
      ) c ),
  'potpisiDemografija', (
      SELECT JSON_AGG(e)
        FROM (SELECT "opsegGodina", 
                     SUM(CASE
                          WHEN "idPola" = 'М' THEN "brojPotpisa"
                          ELSE 0
                         END) "brojPotpisaMuskaraca",
                     SUM(CASE
                          WHEN "idPola" = 'Ж' THEN "brojPotpisa"
                          ELSE 0
                         END) "brojPotpisaZena"
                FROM (SELECT g.IDNIPOl as "idPola",
                             CASE
                              WHEN date_part('year', CURRENT_DATE) - g.GodinaRodjenja <= 24             THEN '18-24'
                              WHEN date_part('year', CURRENT_DATE) - g.GodinaRodjenja between 25 and 34 THEN '25-34'
                              WHEN date_part('year', CURRENT_DATE) - g.GodinaRodjenja between 35 and 44 THEN '35-44'
                              WHEN date_part('year', CURRENT_DATE) - g.GodinaRodjenja between 45 and 54 THEN '45-54'
                              WHEN date_part('year', CURRENT_DATE) - g.GodinaRodjenja between 55 and 64 THEN '55-64'
                              WHEN date_part('year', CURRENT_DATE) - g.GodinaRodjenja between 65 and 74 THEN '65-74'
                              WHEN date_part('year', CURRENT_DATE) - g.GodinaRodjenja between 75 and 84 THEN '75-84'
                              WHEN date_part('year', CURRENT_DATE) - g.GodinaRodjenja >= 85             THEN '85+'
                            END as "opsegGodina",
                           count(*) as "brojPotpisa"
                        FROM ni.NIPotpisInicijative p, ni.NIGradjanin g
                       WHERE p.IDNIInicijativa = i.IDNIInicijativa
                         AND p.IDNIGradjanin = g.IDNIGradjanin
                       GROUP BY 2, 1
                       ORDER BY 2, 1
              ) d 
              GROUP BY 1
              ORDER BY 1
           ) e ))) as text) as sadrzajZaObjavu
  FROM ni.NIInicijativa i
 WHERE i.IDNIInicijativa in
  (SELECT DISTINCT p1.IDNIInicijativa
     FROM ni.NIPotpisInicijative p1
    WHERE p1.trnPotpisa > current_timestamp - CAST(brojPoslednjihSekundi||' seconds' AS Interval) );

END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
