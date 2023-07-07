CREATE OR REPLACE PROCEDURE ni.NITxBaciGresku(IN kodGreske Text, IN idKorisnika Text, IN tekstPoruke Text)
LANGUAGE plpgsql
AS $$
BEGIN
    RAISE EXCEPTION USING ERRCODE = kodGreske, MESSAGE = '[' || idKorisnika || '] '  || tekstPoruke;
END;
$$;
