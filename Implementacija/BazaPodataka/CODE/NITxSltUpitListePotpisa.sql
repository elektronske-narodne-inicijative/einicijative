CREATE OR REPLACE PROCEDURE ni.NITxSltUpitListePotpisa(
    IN  idGradjanina uuid,
    OUT listaPotpisa JSON
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    trnOd timestamp;
BEGIN
    trnOd = now() - interval '1 day' * ni.NITxDajNumerickiParametar('ПериодЛистеПотписаЗаПотписника');
    SELECT json_agg(a)
      INTO listaPotpisa
      FROM (SELECT i.IDNIInicijativa as "idInicijative", 
                   i.NazivInicijative as  "nazivInicijative", 
                   p.Potpis as  "idPotpisa",
                   p.TrnPotpisa "trnZavodjenjaPotpisa"
              FROM ni.NIINicijativa i, ni.NIPotpisInicijative p
             WHERE p.IDNIGradjanin = idGradjanina
              AND p.TrnPotpisa > trnOd
              AND  p.IDNIInicijativa = i.IDNIInicijativa
             ORDER BY p.TrnPotpisa desc
      ) a;
END;
$$;
