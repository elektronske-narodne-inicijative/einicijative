CREATE OR REPLACE PROCEDURE ni.NITxIncIzmeniInicijativu(
    IN  jwtHash Text,
    IN  idInicijative integer,
    IN  idTipaInicijative text,
    IN  nazivInicijative text,
    IN  tekstInicijative text,
    IN  emailZaKontakt text,
    IN  idNivoaVlasti text,
    IN  idJediniceVlasti text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    iPre RECORD;
    iPosle RECORD;
BEGIN
    call ni.NITxIntPristupClanaOdboraZaIzmenu(jwtHash, idInicijative, sesija, gradjanin, iPre);
    -- pretpostavka je da su referencijalni integriteti za tip inicijative, nivo vlasti, opštinu/pokrajinu ako nivo vlasti nije replublika validirani u srednjem sloju
    iPosle = iPre;
    iPosle.IDNITipInicijative = idTipaInicijative;
    iPosle.NazivInicijative = nazivInicijative;
    iPosle.TekstInicijative = tekstInicijative;
    iPosle.EmailZaKontakt = emailZaKontakt;
    iPosle.IDNINivoVlasti = idNivoaVlasti;
    IF idNivoaVlasti = 'О' THEN
        iPosle.IDNIOpstina = idJediniceVlasti;
        iPosle.IDNIPokrajina = null;
    END IF;
    IF idNivoaVlasti = 'П' THEN
        iPosle.IDNIPokrajina = idJediniceVlasti;
        iPosle.IDNIOpstina = null;
    END IF;
    UPDATE ni.NIInicijativa 
       SET IDNITipInicijative = iPosle.IDNITipInicijative, 
           NazivInicijative   = iPosle.NazivInicijative, 
           TekstInicijative   = iPosle.TekstInicijative, 
           EmailZaKontakt     = iPosle.EmailZaKontakt, 
           IDNINivoVlasti     = iPosle.IDNINivoVlasti, 
           IDNIOpstina        = iPosle.IDNIOpstina, 
           IDNIPokrajina      = iPosle.IDNIPokrajina
     WHERE IDNIInicijativa = iPre.IDNIInicijativa;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'PI', iPre, iPosle);
END;
$$;
