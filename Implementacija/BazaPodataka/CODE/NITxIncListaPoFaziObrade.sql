CREATE OR REPLACE PROCEDURE ni.NITxIncListaPoFaziObrade(
    IN  jwtHash Text,
    IN  idFazeObrade Text,
    OUT listaInicijativa JSON
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'И', -- potreban tip sesije
        'Непознат хеш сесије!',
        'Неодговарајући тип сесије - очекује се сесија за члана иницијативног одбора!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    SELECT json_agg(a)
      INTO listaInicijativa
      FROM (SELECT i.IDNIInicijativa as "idInicijative", 
                   i.NazivInicijative as  "nazivInicijative",
                   (SELECT s.Opis FROM ni.NITipInicijative s WHERE s.IDNITipInicijative = i.IDNITipInicijative) as "tipInicijative",
                   i.trnZahteva as  "trnZahteva",
                   i.trnPodnosenja as  "trnPodnosenja",
                   (SELECT count(*) FROM ni.NIClanInicijativnogOdbora s WHERE s.IDNIInicijativa = i.IDNIInicijativa) as "brInicijatora",
                   null as "datumPokretanja",
                   (SELECT count(*) FROM ni.NIPotpisInicijative s WHERE s.IDNIInicijativa = i.IDNIInicijativa) as  "brPotpisa"
              FROM ni.NIINicijativa i
             WHERE i.IDNIFazaObrade = idFazeObrade
               AND i.IDNIInicijativa IN 
                   (SELECT i2.IDNIInicijativa FROM ni.NIINicijativa i2 WHERE i2.IDNIGradjanin = gradjanin.IDNIGradjanin
                    UNION ALL 
                    SELECT cio.IDNIInicijativa FROM ni.NIClanInicijativnogOdbora cio WHERE cio.IDNIGradjanin = gradjanin.IDNIGradjanin)
             ORDER BY i.TrnZahteva desc
      ) a;
END;
$$;
