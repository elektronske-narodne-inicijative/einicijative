-- liquibase formatted sql

-- changeset liquibase:rseni-code-NiTestInicijativa

CREATE OR REPLACE PROCEDURE ni.NiTestInicijativa(OUT idInicijative INT)
LANGUAGE plpgsql
AS $$
DECLARE
    nazivInicijative TEXT;
    tekstInicijative TEXT;
    idInicijatora UUID;
BEGIN
    idInicijative = nextval('ni.SIDNIInicijativa')*10+floor(random()*10);
    nazivInicijative = 'Иницијатива бр. ' || cast(idInicijative as text);
    tekstInicijative = 'Да се брише члан ' || cast((floor(random()*115+1)) as text)  || ' Закона о дивљачи и ловству.';
    -- in rare cases this does not get satisfied, therefore loop
    idInicijatora = null;
    WHILE idInicijatora is null LOOP
        SELECT IDNIGradjanin
          INTO idInicijatora
          FROM NIGradjanin
         WHERE InicijatorovoIme is not null
           AND random() < 0.0001;
    END LOOP;
    INSERT INTO NIInicijativa
     (IDNIInicijativa, IDNIGradjanin, IDNITipInicijative, NazivInicijative, TekstInicijative, TrnZahteva, IDNIFazaObrade, EmailZaKontakt, IDNINivoVlasti, TrnPodnosenja, DatumAktiviranja)
    VALUES
     (idInicijative, idInicijatora, 'ОИ', nazivInicijative, tekstInicijative, now(), 'А', cast('a' || substring(cast(idInicijatora as text),1,8) || '@gmail.com' as text),'Р', now(), current_date);
END;
$$;
