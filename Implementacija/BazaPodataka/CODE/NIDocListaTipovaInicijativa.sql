CREATE OR REPLACE FUNCTION ni.NIDocListaTipovaInicijativa()
RETURNS text AS
$$
DECLARE
  rezultat text;
BEGIN
    SELECT cast(json_agg(a) as text)
      INTO rezultat
      FROM (SELECT IDNITipInicijative as "idTipInicijative", Opis as "opis", Sortiranje as "sortiranje" 
              FROM ni.NITipInicijative 
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
