CREATE OR REPLACE PROCEDURE ni.NITxPtpPotpisiInicijativu(
    IN  jwtHash Text,
    IN  idInicijative integer,
    OUT idPotpisa uuid,
    OUT trnZavodjenjaPotpisa timestamp
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    inicijativa RECORD;
    gradjanin RECORD;
    opstina RECORD;
    slogPotpisa RECORD;
    potpis UUID;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'П', -- potreban tip sesije
        'Непознат хеш сесије у потписивању иницијативе!',
        'Неодговарајући тип сесије - очекује се сесија за потписивање!',
        'Истекао је период важења пријаве - молимо пријавите се поново!',
         true, -- da li je istek JWT bitan
        'Истекао је период важења пријаве - молимо пријавите се поново! (JWT)'
    );
    call ni.NITxIntDajInicijativu(sesija, idInicijative, inicijativa, 'Непозната иницијатива за потписивање!');
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    call ni.NITxIntDajOpstinu(gradjanin.IDNIOpstina, opstina);
    IF inicijativa.IDNINivoVlasti = 'О' THEN
        IF inicijativa.IDNIOPstina != gradjanin.IDNIOpstina THEN
            call ni.NITxBaciGresku('NIRNS',sesija.IDNIKorisnik,'Грађанин није бирач у општини за коју се иницијатива спроводи!');
        END IF;
    ELSIF inicijativa.IDNINivoVlasti = 'П' THEN
        IF inicijativa.IDNIPokrajina is distinct from opstina.IDNIPokrajina THEN
            call ni.NITxBaciGresku('NIRNS',sesija.IDNIKorisnik,'Грађанин није бирач у покрајини за коју се иницијатива спроводи!');
        END IF;
    END IF; -- inicijative na nivou republike može da potpiše svaki građanin 
    -- ako je već potpisano vrati prethodne podatke (samo će se razlikovati digitalni potpis aplikacije, koji se ne čuva)
    BEGIN
        SELECT *
          INTO STRICT slogPotpisa
          FROM ni.NIPotpisInicijative
         WHERE IDNIInicijativa = idInicijative
           AND IDNIGradjanin = gradjanin.IDNIGradjanin;
        potpis = slogPotpisa.potpis;
        trnZavodjenjaPotpisa = slogPotpisa.TrnPotpisa;
    EXCEPTION
        WHEN no_data_found THEN
            potpis = gen_random_uuid ();
            trnZavodjenjaPotpisa = now();
            INSERT INTO ni.NIPotpisInicijative(IDNIInicijativa, IDNIGradjanin, Potpis, TrnPotpisa, PotpisNaSalteru) VALUES (idInicijative, gradjanin.IDNIGradjanin, potpis, trnZavodjenjaPotpisa, false);
    END;
END;
$$;
