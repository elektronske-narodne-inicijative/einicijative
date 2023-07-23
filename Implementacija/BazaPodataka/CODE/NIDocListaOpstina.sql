CREATE OR REPLACE FUNCTION ni.NIDocListaOpstina()
RETURNS text AS
$$
DECLARE
  rezultat text;
BEGIN
    SELECT cast(json_agg(a) as text)
      INTO rezultat
      FROM (SELECT IDNIOpstina as "idOpstine", Opis as "opis", Sortiranje as "sortiranje", BrojRegistrovanihGlasaca as "brojRegistrovanihGlasaca", IDNIUpravniOkrug as "idUpravnogOkruga", IDNIPokrajina as "idPokrajine"
              FROM ni.NIOpstina
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
