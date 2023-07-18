CREATE OR REPLACE PROCEDURE ni.NITxSltUpitPotpisa(
    in  idGradjanina uuid,
    IN  idInicijative integer,
    OUT nazInicijative Text,
    OUT idPotpisa uuid,
    OUT trnZavodjenjaPotpisa timestamp
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
BEGIN
    SELECT NazivInicijative
      INTO nazInicijative
      FROM ni.NIInicijativa i
     WHERE i.IDNIInicijativa = idInicijative;
    SELECT Potpis, TrnZavodjenjaPotpisa
      INTO idPotpisa, trnZavodjenjaPotpisa
      FROM ni.NIPotpisInicijative pi
     WHERE pi.IDNIInicijativa = idInicijative
       AND pi.IDNIGradjanin = idGradjanina;
END;
$$;
