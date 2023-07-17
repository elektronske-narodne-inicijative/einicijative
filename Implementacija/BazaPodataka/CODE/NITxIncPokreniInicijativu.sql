CREATE OR REPLACE PROCEDURE ni.NITxIncPokreniInicijativu(
    IN  jwtHash Text,
    IN  idInicijative integer
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
    inicijativa RECORD;
    opstina RECORD;
    brPotpisa integer;
BEGIN
    call ni.NITxIntPristupClanaOdbora(jwtHash, idInicijative, sesija, gradjanin, inicijativa);
    IF inicijativa.IDNIFazaObrade != 'А' THEN
        call ni.NITxBaciGresku('NIFIO',sesija.IDNIKorisnik,'Иницијатива није активна - не можете је покренути!');
    END IF;
    SELECT count(*)
      INTO brPotpisa
      FROM ni.NIPotpisInicijative
     WHERE IDNIInicijativa = idInicijative;
    -- Proveri da li je prikupljeno dovoljno potpisa
    IF inicijativa.IDNITipInicijative = 'ПУ' THEN
        IF brPotpisa < ni.NITxDajNumerickiParametar('БројПотписаЗаЗахтевЗаПроменуУстава') THEN
            call ni.NITxBaciGresku('NIFIK',sesija.IDNIKorisnik,'Не можете покренути иницијативу док се не прикупи квалификујући број потписа');
        END IF;
    ELSIF inicijativa.IDNITipInicijative = 'РФ' THEN
        IF inicijativa.IDNINivoVlasti = 'Р' THEN
            IF brPotpisa < ni.NITxDajNumerickiParametar('БројПотписаЗаРепубличкиРеферендум') THEN
                call ni.NITxBaciGresku('NIFIK',sesija.IDNIKorisnik,'Не можете покренути иницијативу док се не прикупи квалификујући број потписа');
            END IF;
        ELSIF inicijativa.IDNINivoVlasti = 'П' THEN
            IF brPotpisa < ni.NITxDajNumerickiParametar('БројПотписаЗаПокрајинскиРеферендум') THEN
                call ni.NITxBaciGresku('NIFIK',sesija.IDNIKorisnik,'Не можете покренути иницијативу док се не прикупи квалификујући број потписа');
            END IF;
        ELSE -- inače je opština
            call ni.NITxIntDajOpstinu(inicijativa.IDNIOpstina, opstina);
            IF brPotpisa < opstina.BrojRegistrovanihGlasaca * 100 / ni.NITxDajNumerickiParametar('ПроценатГласачаЗаОпштинскиРеферендум') THEN
                call ni.NITxBaciGresku('NIFIK',sesija.IDNIKorisnik,'Не можете покренути иницијативу док се не прикупи квалификујући број потписа');
            END IF;            
        END IF;
    ELSE -- neka od inicijativa za promenu zakona
        IF brPotpisa < ni.NITxDajNumerickiParametar('БројПотписаЗаИницијативуЗаПроменуЗакона') THEN
            call ni.NITxBaciGresku('NIFIK',sesija.IDNIKorisnik,'Не можете покренути иницијативу док се не прикупи квалификујући број потписа');
        END IF;
    END IF;
    inicijativa.IDNIFazaObrade = 'П';
    inicijativa.DatumPokretanja = current_date;
    UPDATE ni.NIInicijativa
       SET IDNIFazaObrade  = inicijativa.IDNIFazaObrade,
           DatumPokretanja = inicijativa.DatumPokretanja
     WHERE IDNIInicijativa = idInicijative;
    call ni.NITxIntUpisiUDnevnikPromena(jwtHash,'NP', inicijativa, inicijativa);
END;
$$;
