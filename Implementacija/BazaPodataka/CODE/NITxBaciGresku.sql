CREATE OR REPLACE PROCEDURE ni.NITxBaciGresku(IN kodGreske Text, IN idKorisnika uuid, IN tekstPoruke Text)
LANGUAGE plpgsql
AS $$
BEGIN
    RAISE EXCEPTION USING ERRCODE = kodGreske, MESSAGE = '[' || cast(idKorisnika as text) || '] '  || tekstPoruke;
END;
$$;
