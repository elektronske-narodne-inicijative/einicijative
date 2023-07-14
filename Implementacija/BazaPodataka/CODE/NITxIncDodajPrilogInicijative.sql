CREATE OR REPLACE PROCEDURE ni.NITxIncDodajPrilogInicijative(
    IN  jwtHash Text,
    IN  idInicijative integer,
    in  nazivPriloga text,
    in  urlPriloga text,
    in  sortiranje integer,
    out idPrilogaInicijative integer
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    iPre ni.NIInicijativa%ROWTYPE;
    iPosle ni.NIInicijativa%ROWTYPE;
    pPre ni.NIPrilogInicijative%ROWTYPE;
    pPosle ni.NIPrilogInicijative%ROWTYPE;
BEGIN
    call ni.NITxIntPristupClanaOdboraZaIzmenu(jwtHash, idInicijative, sesija, gradjanin, iPre);
    iPosle = iPre;
    -- pretpostavka je da su referencijalni integriteti za tip inicijative, nivo vlasti, opštinu/pokrajinu ako nivo vlasti nije replublika validirani u srednjem sloju
    pPosle.IDNIInicijativa = iPosle.IDNIInicijativa;
    pPosle.IDNIPrilogInicijative = nextval('ni.SIDNIPrilogInicijative');
    idPrilogaInicijative = pPosle.IDNIPrilogInicijative;
    pPre = pPosle;
    pPosle.NazivPriloga = nazivPriloga;
    pPosle.UrlPriloga = urlPriloga;
    pPosle.Sortiranje = sortiranje;
    pPosle.Obrisan = false;
    INSERT INTO ni.NIPrilogInicijative (IDNIPrilogInicijative, IDNIInicijativa, Sortiranje, NazivPriloga, URLPriloga, Obrisan)
    VALUES (pPosle.IDNIPrilogInicijative, pPosle.IDNIInicijativa, pPosle.Sortiranje, pPosle.NazivPriloga, pPosle.URLPriloga, pPosle.Obrisan);
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'DP', iPre, iPosle, pPre, pPosle);
END;
$$;
