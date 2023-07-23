CREATE OR REPLACE FUNCTION ni.NIDocListaUpravnihOkruga()
RETURNS text AS
$$
DECLARE
  rezultat text;
BEGIN
    SELECT cast(json_agg(a) as text)
      INTO rezultat
      FROM (SELECT IDNIUpravniOkrug as "idUpravnogOkruga", Opis as "opis", Sortiranje as "sortiranje" 
              FROM ni.NIUpravniOkrug 
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
