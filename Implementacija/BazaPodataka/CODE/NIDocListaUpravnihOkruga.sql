-- liquibase formatted sql

-- changeset liquibase:rseni-code-NIDocListaUpravnihOkruga

CREATE OR REPLACE FUNCTION ni.NIDocListaUpravnihOkruga()
RETURNS json AS
$$
DECLARE
  rezultat JSON;
BEGIN
    SELECT json_agg(a)
      INTO rezultat
      FROM (SELECT IDNIUpravniOkrug as "idUpravnogOkruga", Opis as "opis", Sortiranje as "sortiranje" 
              FROM ni.NIUpravniOkrug 
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
