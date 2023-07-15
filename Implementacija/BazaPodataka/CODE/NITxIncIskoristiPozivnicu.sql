CREATE OR REPLACE PROCEDURE ni.NITxIncIskoristiPozivnicu(
    IN  jwtHash Text,
    IN  idInicijative integer,
    IN  pozivnica text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    inicijativa RECORD;
    clanodbora RECORD;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'И', -- potreban tip sesije
        'Непознат хеш сесије у додавању нове иницијативе!',
        'Сесија неодговарајућег типа за употребу позивнице!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    call ni.NITxIntDajInicijativu(sesija, idInicijative, inicijativa, 'Непозната иницијатива за укључење у иницијативни одбор!');
    IF inicijativa.PozivnicaSHA256 is null OR pozivnica is null OR inicijativa.PozivnicaSHA256 != cast(digest(pozivnica,'sha256') as text) THEN
        call ni.NITxBaciGresku('NIPUN',sesija.IDNIKorisnik,'Ваша позивница је неисправна или истекла, молимо тражите да вам се изда нова');
    END IF;
    UPDATE ni.NIInicijativa
       SET PozivnicaSHA256 = null
     WHERE IDNIInicijativa = idInicijative;
    BEGIN
        SELECT *
          INTO STRICT clanodbora
          FROM ni.NIClanInicijativnogOdbora cio
         WHERE cio.IDNIGradjanin = gradjanin.IDNIGradjanin
           AND cio.IDNIInicijativa = idInicijative;
    EXCEPTION
        WHEN no_data_found THEN
            INSERT INTO ni.NIClanInicijativnogOdbora 
              (IDNIGradjanin, IDNIInicijativa, trnPrihvatanjaClanstva) 
            VALUES
              (gradjanin.IDNIGradjanin, idInicijative, current_timestamp);
    END;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'ZI', inicijativa, inicijativa);
END;
$$;
