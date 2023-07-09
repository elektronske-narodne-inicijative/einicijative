CREATE OR REPLACE PROCEDURE ni.NITestTxPtpMethods(
    IN  idKorisnika uuid,
    IN  jwtTimeoutSec integer,
    IN  loopDelaySec  decimal
)
LANGUAGE plpgsql
AS $$
DECLARE
    gradjanin RECORD;
    inicijativa RECORD;
    jwtHash text;
    jwtVrednost json;
    trnIstekaJWT timestamp;
    idPola text;
    godinaRodjenja integer;
    idOpstine text;
    prisutna boolean;
    isteklaSesija boolean;
    istekaoJWT boolean;
    idTipaSesije char(1);
    idTipaKorisnika char(1);
    nazivOpstine text;
    idPotpisa uuid;
    trnZavodjenjaPotpisa timestamp;
    nazivInicijative Text;
    listaPotpisa json;
BEGIN
    SELECT *
      INTO STRICT gradjanin
      FROM ni.NIGradjanin g
     WHERE g.IDNIGradjanin = idKorisnika;
    jwtVrednost = cast('{"a":"'|| cast(gen_random_uuid () as text) ||'"}' as json);
    jwtHash = cast(digest(cast(jwtVrednost as text), 'sha256') as text);
    trnIstekaJWT = cast (current_timestamp + interval '1 second' * jwtTimeoutSec as timestamp);
    -- inicijalizuj
    call ni.NITxPtpNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, gradjanin.IDNIGradjanin, gradjanin.IDNIPol, gradjanin.GodinaRodjenja, gradjanin.IDNIOpstina);
    commit;
    call ni.NITxDajSesijuPoHash(jwtHash, prisutna, isteklaSesija, istekaoJWT, idTipaSesije, idTipaKorisnika);
    call ni.NITxPtpDajProfil(jwtHash, idPola, godinaRodjenja, nazivOpstine);
    -- vrti dok ne potrošiš sesiju
    FOR inicijativa IN SELECT * FROM ni.NIInicijativa LOOP
        call ni.NITxPtpPotpisiInicijativu(jwtHash, inicijativa.IDNIInicijativa, idPotpisa, trnZavodjenjaPotpisa);
        call ni.NITxPtpDetaljiPotpisa(jwtHash, inicijativa.IDNIInicijativa, nazivInicijative, idPotpisa, trnZavodjenjaPotpisa);
        call ni.NITxPtpListaPotpisa(jwtHash, listaPotpisa);
        commit;
        SELECT pg_sleep(loopDelaySec) INTO nazivOpstine;
    END LOOP;
END;
$$;
