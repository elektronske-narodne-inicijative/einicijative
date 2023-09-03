CREATE OR REPLACE PROCEDURE ni.NiTestInicijativa(OUT idInicijative INT)
LANGUAGE plpgsql
AS $$
DECLARE
    nazivInicijative TEXT;
    tekstInicijative TEXT;
    idInicijatora UUID;
    idClanaOdbora UUID;
	idPriloga INT;
	clan1 INT;
	clan2 INT;
	clan3 INT;
BEGIN
    idInicijative = nextval('ni.SIDNIInicijativa')*10+floor(random()*10);
	clan1 = floor(random()*115+1);
	clan2 = floor(random()*115+1);
	clan3 = floor(random()*115+1);
    nazivInicijative = 'Брисање из Закона о ловству чланова: ' || cast(clan1 as text) || ', ' || cast(clan2 as text) || ' и ' || cast(clan3 as text) ;
    tekstInicijative = 'Да се бришу чланови ' || cast(clan1 as text) || ', ' || cast(clan2 as text) || ' и ' || cast(clan3 as text) || ' из Закона о дивљачи и ловству.';
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
    -- dodaj prilog
    idPriloga = nextval('ni.SIDNIPrilogInicijative');
    INSERT INTO NIPrilogInicijative
     (IDNIPrilogInicijative, IDNIInicijativa, Sortiranje, NazivPriloga, URLPriloga, Obrisan)
    VALUES
     (idPriloga, idInicijative, 1, 'Пуни текст народне иницијативе', 'https://www.docdroid.net/0vEHaIS/narodna-inicijativa-za-izmenu-i-dopunu-zakona-o-rudarstvu-i-geoloskim-istrazivanjima-14-2-2022-pdf', false);
    -- dodaj inicijatora u članove odbora
    INSERT INTO NIClanInicijativnogOdbora
     (IDNIGradjanin , IDNIInicijativa, TrnPrihvatanjaClanstva)
    VALUES
     (idInicijatora, idInicijative, current_timestamp);
    -- dodaj još 4 random člana u članove odbora
    FOR i IN 1..4 LOOP
        idClanaOdbora = null;
        WHILE idClanaOdbora is null LOOP
            SELECT IDNIGradjanin
              INTO idClanaOdbora
              FROM NIGradjanin
             WHERE InicijatorovoIme is not null
               AND IDNIGradjanin NOT IN 
			       (SELECT IDNIGradjanin FROM NIClanInicijativnogOdbora WHERE IDNIInicijativa = idInicijative)
               AND random() < 0.0001;
        END LOOP;
        INSERT INTO NIClanInicijativnogOdbora
         (IDNIGradjanin , IDNIInicijativa, TrnPrihvatanjaClanstva)
        VALUES
         (idClanaOdbora, idInicijative, current_timestamp);
    END LOOP;
END;
$$;
