CREATE OR REPLACE PROCEDURE ni.NITxIncPodnesiZahtev(
    IN  jwtHash Text,
    IN  idInicijative integer
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    inicijativa RECORD;
    brClanovaIONI integer;
BEGIN
    call ni.NITxIntPristupClanaOdbora(jwtHash, idInicijative, sesija, gradjanin, inicijativa);
    IF inicijativa.IDNIFazaObrade != 'У' THEN
        call ni.NITxBaciGresku('NIFIP',sesija.IDNIKorisnik,'Иницијатива више није у фази припреме - не можете је поднети на одобрење скупштини!');
    END IF;
    SELECT count(*)
      INTO brClanovaIONI
      FROM ni.NIClanInicijativnogOdbora cio
     WHERE cio.IDNIInicijativa = idInicijative;
    IF brClanovaIONI + 1 < ni.NITxDajNumerickiParametar('НајмањеЧлановаИницијативногОдбора') THEN
        call ni.NITxBaciGresku('NIFIM',sesija.IDNIKorisnik,'Не можете поднети иницијативу на одобрење скупштини, иницијатива нема довољан број чланова иницијативног одбора');
    END IF;
    inicijativa.IDNIFazaObrade = 'В';
    UPDATE ni.NIInicijativa
       SET IDNIFazaObrade = inicijativa.IDNIFazaObrade
     WHERE IDNIInicijativa = idInicijative;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'NP', inicijativa, inicijativa);
END;
$$;
