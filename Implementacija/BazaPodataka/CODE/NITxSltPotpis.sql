CREATE OR REPLACE PROCEDURE ni.NITxSltPotpis(
    IN  idGradjanina uuid,
    IN  idPola text,
    IN  godinaRodjenja integer,
    IN  idOpstine text,
    in  idInicijative integer,
    in  podaciSaSalteraPotpisa text,
    out idPotpisa uuid,
    out trnZavodjenjaPotpisa timestamp
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    inicijativa RECORD;
    opstina RECORD;
BEGIN
    BEGIN
        SELECT *
          INTO STRICT inicijativa
          FROM ni.NIInicijativa i
         WHERE i.IDNIInicijativa = idInicijative;
    EXCEPTION
        WHEN no_data_found THEN
            call ni.NITxBaciGresku('NIRNI',idGradjanina,'Непозната иницијатива за потписивање!');
    END;
    call ni.NITxIntPodaciOGradjaninu(idGradjanina,idPola, godinaRodjenja, idOpstine, null, true);
    call ni.NITxIntDajOpstinu(idOpstine, opstina);
    call ni.NITxIntPotpisiInicijativu(true, -- salterski potpis
        idGradjanina, idPola, godinaRodjenja, idOpstine, opstina.IDNIPokrajina, 
        inicijativa.IDNIInicijativa, inicijativa.IDNINivoVlasti, inicijativa.IDNIOpstina, inicijativa.IDNIPokrajina,
        podaciSaSalteraPotpisa, idPotpisa, trnZavodjenjaPotpisa);
END;
$$;
