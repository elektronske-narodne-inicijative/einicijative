CREATE OR REPLACE PROCEDURE ni.NITxIncPripremiPozivnicu(
    IN  jwtHash Text,
    IN  idInicijative integer,
    OUT pozivnica text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    inicijativa RECORD;
BEGIN
    call ni.NITxIntPristupClanaOdboraZaIzmenu(jwtHash, idInicijative, sesija, gradjanin, inicijativa);
    IF inicijativa.PozivnicaSHA256 is not null THEN
        call ni.NITxBaciGresku('NIPI2',sesija.IDNIKorisnik,'Не можете тражити нову позивницу док текућа не буде искоришћена или поништена');
    END IF;
    pozivnica = cast(digest(cast(gen_random_uuid () as text) || cast(gen_random_uuid () as text) || cast(gen_random_uuid () as text),'sha256') as text);
    UPDATE ni.NIInicijativa
       SET PozivnicaSHA256 = cast(digest(pozivnica,'sha256') as text)
     WHERE IDNIInicijativa = idInicijative;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'ZP', inicijativa, inicijativa);
END;
$$;
