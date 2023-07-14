CREATE OR REPLACE PROCEDURE ni.NITxOvlListaZaOdobrenje(
    IN  jwtHash Text,
    OUT listaInicijativa JSON
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    ovlice RECORD;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'О', -- potreban tip sesije
        'Непознат хеш сесије у упиту листе за одобрење поднетих иницијатива!',
        'Неодговарајући тип сесије - очекује се сесија за овлашћено лице!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajOvlascenoLice(sesija, ovlice,'Недостају подаци о овлашћеном лицу за ИД корисника из сесије!');
    SELECT json_agg(a)
      INTO listaInicijativa
      FROM (SELECT i.IDNIInicijativa as "idInicijative", 
                   i.NazivInicijative as  "nazivInicijative",
                   (SELECT s.Opis FROM ni.NITipInicijative s WHERE s.IDNITipInicijative = i.IDNITipInicijative) as "tipInicijative",
                   i.trnZahteva as  "trnZahteva",
                   i.trnPodnosenja as  "trnPodnosenja",
                   (SELECT count(*) FROM ni.NIClanInicijativnogOdbora s WHERE s.IDNIInicijativa = i.IDNIInicijativa) as "brInicijatora",
                   null as "datumPokretanja",
                   0 as  "brPotpisa"
              FROM ni.NIINicijativa i
             WHERE i.IDNIFazaObrade = 'В'
               AND i.IDNINivoVlasti = ovlice.IDNINivoVlasti
               AND (    i.IDNINivoVlasti = 'Р'
                    OR (i.IDNINivoVlasti = 'О' AND i.IDNIOpstina = ovlice.IDNIOpstina)
                    OR (i.IDNINivoVlasti = 'П' AND i.IDNIPokrajina = ovlice.IDNIPokrajina)
                   )
             ORDER BY i.TrnPodnosenja asc
      ) a;
END;
$$;
