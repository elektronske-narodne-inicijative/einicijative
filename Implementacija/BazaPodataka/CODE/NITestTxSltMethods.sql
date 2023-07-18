CREATE OR REPLACE PROCEDURE ni.NITestTxSltMethods(
    IN  idKorisnika uuid,
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
    FOR inicijativa IN SELECT * FROM ni.NIInicijativa LOOP
        call ni.NITxSltPotpis(gradjanin.IDNIGradjanin, gradjanin.IDNIPol, gradjanin.GodinaRodjenja, gradjanin.IDNIOpstina, inicijativa.IDNIInicijativa, 'Штагод подаци са шалтера бла бла', idPotpisa, trnZavodjenjaPotpisa);
        commit;
        call ni.NITxSltUpitPotpisa(gradjanin.IDNIGradjanin, inicijativa.IDNIInicijativa, nazivInicijative, idPotpisa, trnZavodjenjaPotpisa);
        call ni.NITxSltUpitListePotpisa(gradjanin.IDNIGradjanin, listaPotpisa);
        SELECT pg_sleep(loopDelaySec) INTO nazivOpstine;
    END LOOP;
END;
$$;
