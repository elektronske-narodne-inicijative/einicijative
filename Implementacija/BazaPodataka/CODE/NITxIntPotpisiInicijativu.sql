CREATE OR REPLACE PROCEDURE ni.NITxIntPotpisiInicijativu(
    IN  salterskiPotpis boolean,
    IN  idGradjanina uuid,
    IN  idPola text,
    IN  godinaRodjenja integer,
    IN  idOpstineGradjanina text,
    IN  idPokrajineGradjanina text,
    in  idInicijative integer,
    in  idNivoVlastiInicijative text,
    IN  idOpstineInicijative text,
    IN  idPokrajineInicijative text,
    in  podaciSaSalteraPotpisa text,
    out idPotpisa uuid,
    out trnZavodjenjaPotpisa timestamp
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    slogPotpisa RECORD;
    potpis UUID;
BEGIN
    IF idNivoVlastiInicijative = 'О' THEN
        IF idOpstineInicijative != idOpstineGradjanina THEN
            call ni.NITxBaciGresku('NIRNS',idGradjanina,'Грађанин није бирач у општини за коју се иницијатива спроводи!');
        END IF;
    ELSIF idNivoVlastiInicijative = 'П' THEN
        IF idPokrajineInicijative is distinct from idPokrajineGradjanina THEN
            call ni.NITxBaciGresku('NIRNS',idGradjanina,'Грађанин није бирач у покрајини за коју се иницијатива спроводи!');
        END IF;
    END IF; -- inicijative na nivou republike može da potpiše svaki građanin 
    -- ako je već potpisano vrati prethodne podatke (samo će se razlikovati digitalni potpis aplikacije, koji se ne čuva)
    BEGIN
        SELECT *
          INTO STRICT slogPotpisa
          FROM ni.NIPotpisInicijative
         WHERE IDNIInicijativa = idInicijative
           AND IDNIGradjanin = idGradjanina;
        potpis = slogPotpisa.potpis;
        trnZavodjenjaPotpisa = slogPotpisa.TrnPotpisa;
    EXCEPTION
        WHEN no_data_found THEN
            potpis = gen_random_uuid ();
            trnZavodjenjaPotpisa = now();
            INSERT INTO ni.NIPotpisInicijative(IDNIInicijativa, IDNIGradjanin, Potpis, TrnPotpisa, PotpisNaSalteru) VALUES (idInicijative, idGradjanina, potpis, trnZavodjenjaPotpisa, false);
    END;
END;
$$;
