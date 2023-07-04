-- liquibase formatted sql

-- changeset liquibase:rseni-code-NIDocListaFazaObrade

CREATE OR REPLACE FUNCTION ni.NIDocListaFazaObrade()
RETURNS json AS
$$
DECLARE
  rezultat JSON;
BEGIN
    SELECT json_agg(a)
      INTO rezultat
      FROM (SELECT IDNIFazaObrade as "idFazeObrade", Opis as "opis", Sortiranje as "sortiranje", DozvoljeneSledeceFaze as "dozvoljeneSledeceFaze"
              FROM ni.NIFazaObrade
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
