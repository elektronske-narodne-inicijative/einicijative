CREATE OR REPLACE FUNCTION ni.NIDocListaNivoaVlasti()
RETURNS text AS
$$
DECLARE
  rezultat text;
BEGIN
    SELECT cast(json_agg(a) as text)
      INTO rezultat
      FROM (SELECT IDNINivoVlasti as "idNivoaVlasti", Opis as "opis", Sortiranje as "sortiranje" 
              FROM ni.NINivoVlasti
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
