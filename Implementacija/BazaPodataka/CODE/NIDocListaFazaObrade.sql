CREATE OR REPLACE FUNCTION ni.NIDocListaFazaObrade()
RETURNS text AS
$$
DECLARE
  rezultat text;
BEGIN
    SELECT cast(json_agg(a) as text)
      INTO rezultat
      FROM (SELECT IDNIFazaObrade as "idFazeObrade", Opis as "opis", Sortiranje as "sortiranje", DozvoljeneSledeceFaze as "dozvoljeneSledeceFaze"
              FROM ni.NIFazaObrade
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
