CREATE OR REPLACE PROCEDURE ni.NITxIncDodajInicijativu(
    IN  jwtHash Text,
    IN  idTipaInicijative text,
    IN  nazivInicijative text,
    IN  tekstInicijative text,
    IN  emailZaKontakt text,
    IN  idNivoaVlasti text,
    IN  idJediniceVlasti text,
    OUT idInicijative integer
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    iPre ni.NIInicijativa%ROWTYPE;
    iPosle ni.NIInicijativa%ROWTYPE;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'И', -- potreban tip sesije
        'Непознат хеш сесије у додавању нове иницијативе!',
        'Сесија неодговарајућег типа за додавање нове иницијативе!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    -- pretpostavka je da su referencijalni integriteti za tip inicijative, nivo vlasti, opštinu/pokrajinu ako nivo vlasti nije replublika validirani u srednjem sloju
    iPosle.IDNIGradjanin = gradjanin.IDNIGradjanin;
    iPosle.IDNITipInicijative = idTipaInicijative;
    iPosle.NazivInicijative = nazivInicijative;
    iPosle.TekstInicijative = tekstInicijative;
    iPosle.TrnZahteva = now();
    iPosle.IDNIFazaObrade = 'У';
    iPosle.EmailZaKontakt = emailZaKontakt;
    iPosle.IDNINivoVlasti = idNivoaVlasti;
    IF idNivoaVlasti = 'О' THEN
        iPosle.IDNIOpstina = idJediniceVlasti;
    END IF;
    IF idNivoaVlasti = 'П' THEN
        iPosle.IDNIPokrajina = idJediniceVlasti;
    END IF;
    iPosle.IDNIInicijativa = nextval('ni.SIDNIInicijativa')*10+floor(random()*10);
    idInicijative = iPosle.IDNIInicijativa;
    INSERT INTO ni.NIInicijativa 
    (IDNIInicijativa, IDNIGradjanin, IDNITipInicijative, NazivInicijative, TekstInicijative, TrnZahteva, IDNIFazaObrade, EmailZaKontakt, IDNINivoVlasti, IDNIOpstina, IDNIPokrajina)
    VALUES
    (iPosle.IDNIInicijativa, iPosle.IDNIGradjanin, iPosle.IDNITipInicijative, iPosle.NazivInicijative, iPosle.TekstInicijative, iPosle.TrnZahteva, iPosle.IDNIFazaObrade, 
     iPosle.EmailZaKontakt, iPosle.IDNINivoVlasti, iPosle.IDNIOpstina, iPosle.IDNIPokrajina);
    iPre.IDNIInicijativa = iPosle.IDNIInicijativa;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'DI', iPre, iPosle);
END;
$$;
