CREATE OR REPLACE PROCEDURE ni.NITxIncNovaSesija(
    IN  jwtHash text,
    IN  jwtVrednost text,
    IN  trnIstekaJWT timestamp,
    IN  idKorisnika uuid,
    IN  idPola text,
    IN  godinaRodjenja integer,
    IN  idOpstine text,
    IN  imePrezime text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
BEGIN
    CALL ni.NITxIntGradjaninNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, cast('И' as char), idKorisnika, idPola, godinaRodjenja, idOpstine, imePrezime);
END;
$$;
