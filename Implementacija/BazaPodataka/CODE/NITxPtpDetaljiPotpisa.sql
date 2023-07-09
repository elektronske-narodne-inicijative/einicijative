CREATE OR REPLACE PROCEDURE ni.NITxPtpDetaljiPotpisa(
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
        'Непознат хеш сесије у упиту потписа иницијативе!',
        'Неодговарајући тип сесије - очекује се сесија за потписивање!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajInicijativu(sesija, idInicijative, inicijativa, 'Непозната иницијатива за потписивање!');
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    SELECT *
      INTO slogPotpisa
      FROM ni.NIPotpisInicijative
     WHERE IDNIInicijativa = idInicijative
       AND IDNIGradjanin = gradjanin.IDNIGradjanin;
    potpis = slogPotpisa.potpis;
    nazivInicijative = inicijativa.NazivInicijative;
    trnZavodjenjaPotpisa = slogPotpisa.TrnPotpisa;
END;
$$;
