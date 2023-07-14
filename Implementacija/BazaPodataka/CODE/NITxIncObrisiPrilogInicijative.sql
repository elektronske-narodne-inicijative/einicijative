CREATE OR REPLACE PROCEDURE ni.NITxIncObrisiPrilogInicijative(
    IN  jwtHash Text,
    IN  idPrilogaInicijative integer
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    iPre RECORD;
    iPosle RECORD;
    pPre RECORD;
    pPosle RECORD;
BEGIN
    SELECT *
      INTO STRICT pPre
      FROM  ni.NIPrilogInicijative pi
     WHERE pi.IDNIPrilogInicijative = idPrilogaInicijative;
    pPosle = pPre;
    call ni.NITxIntPristupClanaOdboraZaIzmenu(jwtHash, pPre.IDNIInicijativa, sesija, gradjanin, iPre);
    iPosle = iPre;
    pPosle.Obrisan = true;
    UPDATE ni.NIPrilogInicijative 
       SET Obrisan = pPosle.Obrisan
     WHERE IDNIPrilogInicijative = pPre.IDNIPrilogInicijative;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'OP', iPre, iPosle, pPre, pPosle);
END;
$$;
