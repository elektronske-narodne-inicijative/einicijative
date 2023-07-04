-- liquibase formatted sql

-- changeset liquibase:rseni-code-NIDocListaTipovaInicijativa

CREATE OR REPLACE FUNCTION ni.NIDocListaTipovaInicijativa()
RETURNS json AS
$$
DECLARE
  rezultat JSON;
BEGIN
    SELECT json_agg(a)
      INTO rezultat
      FROM (SELECT IDNITipInicijative as "idTipInicijative", Opis as "opis", Sortiranje as "sortiranje" 
              FROM ni.NITipInicijative 
             ORDER BY sortiranje) a;
    RETURN rezultat;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
