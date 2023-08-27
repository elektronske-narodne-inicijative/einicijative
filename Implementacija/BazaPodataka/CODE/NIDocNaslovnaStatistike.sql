CREATE OR REPLACE FUNCTION ni.NIDocNaslovnaStatistike()
RETURNS text AS
$$
DECLARE
    rezultat text;
BEGIN
    SELECT cast (JSONB_STRIP_NULLS(
      JSONB_BUILD_OBJECT(
          'brojAktivnih',               (SELECT count(*) from ni.NIInicijativa s where s.IDNIFazaObrade = 'А'),
          'brojPokrenutih',             (SELECT count(*) from ni.NIInicijativa s where s.IDNIFazaObrade = 'П' and s.DatumOdluke >= CURRENT_DATE - INTERVAL '1 YEAR'),
          'brojKompletiranih',          (SELECT count(*) from ni.NIInicijativa s where s.IDNIFazaObrade = 'К'),
          'petTrenutnoNajpopularnijih', (
              SELECT JSON_AGG(a)
                FROM (SELECT i.IDNIInicijativa "ID",
                             i.NazivInicijative as "Naziv",
                             count(*) as "brojPotpisa"
                        FROM ni.NIInicijativa i, ni.NIPotpisInicijative p
                       WHERE p.IDNIInicijativa = i.IDNIInicijativa
                         AND p.TrnPotpisa >= CURRENT_TIMESTAMP - INTERVAL '1 HOUR'
                       GROUP BY 1,2
                       ORDER BY 3 DESC
                       LIMIT 5
             ) a ))) as text)
  INTO rezultat;

    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
