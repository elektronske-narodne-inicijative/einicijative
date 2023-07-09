CREATE OR REPLACE PROCEDURE ni.NITxPtpListaPotpisa(
    IN  jwtHash Text,
    OUT listaPotpisa JSON
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    trnOd timestamp;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'П', -- potreban tip sesije
        'Непознат хеш сесије у упиту листе потписа иницијативе!',
        'Неодговарајући тип сесије - очекује се сесија за потписивање!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    trnOd = now() - interval '1 day' * ni.NITxDajNumerickiParametar('ПериодЛистеПотписаЗаПотписника');
    SELECT json_agg(a)
      INTO listaPotpisa
      FROM (SELECT i.IDNIInicijativa as "idInicijative", 
                   i.NazivInicijative as  "nazivInicijative", 
                   p.Potpis as  "idPotpisa",
                   p.TrnPotpisa "trnZavodjenjaPotpisa"
              FROM ni.NIINicijativa i, ni.NIPotpisInicijative p
             WHERE p.IDNIGradjanin = gradjanin.IDNIGradjanin
              AND p.TrnPotpisa > trnOd
              AND  p.IDNIInicijativa = i.IDNIInicijativa
             ORDER BY p.TrnPotpisa desc
      ) a;
END;
$$;
