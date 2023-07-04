CREATE OR REPLACE FUNCTION ni.NIDocListaNivoaVlasti()
RETURNS json AS
$$
DECLARE
  rezultat JSON;
BEGIN
    SELECT json_agg(a)
      INTO rezultat
      FROM (SELECT IDNINivoVlasti as "idNivoaVlasti", Opis as "opis", Sortiranje as "sortiranje" 
              FROM ni.NINivoVlasti
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
