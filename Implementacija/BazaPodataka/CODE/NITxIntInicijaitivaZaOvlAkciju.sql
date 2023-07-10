CREATE OR REPLACE PROCEDURE ni.NITxIntInicijaitivaZaOvlAkciju(
    IN  jwtHash Text,
    IN  idInicijative integer,
    OUT sesija RECORD,
    OUT ovlice RECORD,
    OUT inicijativa RECORD
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'О', -- potreban tip sesije
        'Непознат хеш сесије!',
        'Неодговарајући тип сесије - очекује се сесија за овлашћено лице!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajInicijativu(sesija, idInicijative, inicijativa, 'Непозната иницијатива:' || coalesce(cast(idInicijative as text),'(NULL)'));
    call ni.NITxIntDajOvlascenoLice(sesija, ovlice,'Недостају подаци о овлашћеном лицу за ИД корисника из сесије!');
    -- prvo proveri nadležnost
    IF inicijativa.IDNINivoVlasti != ovlice.IDNINivoVlasti THEN
        call ni.NITxBaciGresku('NIRNN',sesija.IDNIKorisnik,'Овлашћено лице не заступа одговарајући ниво власти!');
    ELSIF inicijativa.IDNINivoVlasti = 'П' THEN
        IF inicijativa.IDNIPokrajina is distinct from ovlice.IDNIPokrajina THEN
            call ni.NITxBaciGresku('NIRNN',sesija.IDNIKorisnik,'Овлашћено лице не заступа покрајину за коју је захтевана иницијатива!');
        END IF;
    ELSIF inicijativa.IDNINivoVlasti = 'О' THEN
        IF inicijativa.IDNIOpstina is distinct from ovlice.IDNIOpstina THEN
            call ni.NITxBaciGresku('NIRNN',sesija.IDNIKorisnik,'Овлашћено лице не заступа општину за коју је захтевана иницијатива!');
        END IF;
    END IF; 
END;
$$;
