CREATE OR REPLACE PROCEDURE ni.NITxPtpNovaSesija(
    IN  jwtHash text,
    IN  jwtVrednost json,
    IN  trnIstekaJWT timestamp,
    IN  idKorisnika uuid,
    IN  idPola text,
    IN  godinaRodjenja integer,
    IN  idOpstine text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
BEGIN
    CALL ni.NITxIntGradjaninNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, cast('П' as char), idKorisnika, idPola, godinaRodjenja, idOpstine, null);
END;
$$;
