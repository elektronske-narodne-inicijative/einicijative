CREATE OR REPLACE PROCEDURE ni.NITxIncIzmeniPrilogInicijative(
    IN  jwtHash Text,
    IN  idPrilogaInicijative integer,
    IN  nazivPriloga text,
    IN  urlPriloga text,
    IN  sortiranje integer
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
    SELECT *
      INTO STRICT pPre
      FROM  ni.NIPrilogInicijative pi
     WHERE pi.IDNIPrilogInicijative = idPrilogaInicijative;
    call ni.NITxIntPristupClanaOdboraZaIzmenu(jwtHash, pPre.IDNIInicijativa, sesija, gradjanin, iPre);
    iPosle = iPre;
    pPosle = pPre;
    pPosle.NazivPriloga = nazivPriloga;
    pPosle.UrlPriloga = urlPriloga;
    pPosle.Sortiranje = sortiranje;
    UPDATE ni.NIPrilogInicijative 
       SET Sortiranje   = pPosle.Sortiranje, 
           NazivPriloga = pPosle.NazivPriloga, 
           URLPriloga   = pPosle.URLPriloga
     WHERE IDNIPrilogInicijative = pPre.IDNIPrilogInicijative;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'PP', iPre, iPosle, pPre, pPosle);
END;
$$;
