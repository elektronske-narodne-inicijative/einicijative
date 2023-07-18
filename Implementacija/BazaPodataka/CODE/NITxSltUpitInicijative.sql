CREATE OR REPLACE PROCEDURE ni.NITxSltUpitInicijative(
    IN  idInicijative integer,
    OUT nazivInicijative text,
    OUT tipInicijative text,
    OUT nivoVlasti text,
    OUT datumAktiviranja date
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
BEGIN
    SELECT i.NazivInicijative,
           (SELECT s.Opis FROM ni.NITipInicijative s WHERE s.IDNITipInicijative = i.IDNITipInicijative) as TipInicijative,
           (SELECT s.Opis FROM ni.NINivoVlasti s WHERE s.IDNINivoVlasti = i.IDNINivoVlasti) as NivoVlasti,
           i.DatumAktiviranja
      INTO nazivInicijative, tipInicijative, nivoVlasti, datumAktiviranja
      FROM ni.NIInicijativa i
     WHERE i.IDNIInicijativa = idInicijative;
END;
$$;
