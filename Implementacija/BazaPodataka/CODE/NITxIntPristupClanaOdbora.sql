CREATE OR REPLACE PROCEDURE ni.NITxIntPristupClanaOdbora(
    IN  jwtHash Text,
    IN  idInicijative integer,
    OUT sesija RECORD,
    OUT gradjanin RECORD,
    OUT inicijativa RECORD
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
   dummy integer;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'И', -- potreban tip sesije
        'Непознат хеш сесије!',
        'Неодговарајући тип сесије - очекује се сесија за члана иницијативног одбора!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajInicijativu(sesija, idInicijative, inicijativa, 'Непозната иницијатива:' || coalesce(cast(idInicijative as text),'(NULL)'));
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    -- prvo proveri da li je inicijator ili član odbora, pa tek onda da li je inicijativa još u fazi pripreme, jer je pokušaj nekoga ko nije član odbora da menja inicijativu verovatniji slučaj napada na sistem
    IF inicijativa.IDNIGradjanin != gradjanin.IDNIGradjanin THEN
        BEGIN
            SELECT 1
              INTO STRICT dummy
              FROM ni.NIClanInicijativnogOdbora cio
             WHERE cio.IDNIGradjanin = gradjanin.IDNIGradjanin
               AND cio.IDNIInicijativa = inicijativa.IDNIInicijativa;
        EXCEPTION
            WHEN no_data_found THEN
                call ni.NITxBaciGresku('NIRNO',sesija.IDNIKorisnik,'Нисте члан иницијативног одбора - не можете мењати ову иницијативу!');
        END;
    END IF;
END;
$$;
