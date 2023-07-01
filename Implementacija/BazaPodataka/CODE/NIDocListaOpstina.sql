CREATE OR REPLACE FUNCTION ni.NIDocListaOpstina()
RETURNS json AS
$$
DECLARE
  rezultat JSON;
BEGIN
    SELECT json_agg(a)
      INTO rezultat
      FROM (SELECT IDNIOpstina as "idOpstine", Opis as "opis", Sortiranje as "sortiranje", BrojRegistrovanihGlasaca as "brojRegistrovanihGlasaca", IDNIUpravniOkrug as "idUpravnogOkruga", IDNIPokrajina as "idPokrajine"
              FROM ni.NIOpstina
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
