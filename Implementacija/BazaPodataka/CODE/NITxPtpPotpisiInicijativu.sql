CREATE OR REPLACE PROCEDURE ni.NITxPtpPotpisiInicijativu(
    IN  jwtHash Text,
    IN  idInicijative integer,
    OUT nazivInicijative Text,
    OUT idPotpisa uuid,
    OUT trnZavodjenjaPotpisa timestamp
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    inicijativa ni.NIINicijativa%ROWTYPE;
    gradjanin ni.NIGradjanin%ROWTYPE;
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
    call ni.NITxIntPotpisiInicijativu(false, -- nije salterski potpis
        gradjanin.IDNIGradjanin, gradjanin.IDNIPol, gradjanin.GodinaRodjenja, gradjanin.IDNIOpstina, opstina.IDNIPokrajina, 
        inicijativa.IDNIInicijativa, inicijativa.IDNINivoVlasti, inicijativa.IDNIOpstina, inicijativa.IDNIPokrajina,
        null, idPotpisa, trnZavodjenjaPotpisa);
    nazivInicijative = inicijativa.nazivInicijative;
END;
$$;
