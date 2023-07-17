CREATE OR REPLACE PROCEDURE ni.NITestTxIncMethods(
    IN  jwtTimeoutSec integer
)
LANGUAGE plpgsql
AS $$
DECLARE
    idKorisnikaSavo uuid;
    idKorisnikaZlatko uuid;
    idKorisnikaLjiljana uuid;
    idKorisnikaMarina uuid;
    idKorisnikaKristina uuid;
    gradjanin RECORD;
    inicijativa RECORD;
    jwtHash text;
    jwtVrednost json;
    trnIstekaJWT timestamp;
    idPola text;
    godinaRodjenja integer;
    idOpstine text;
    imePrezime text;
    emailAdresa text;
    biografija text;
    prisutna boolean;
    isteklaSesija boolean;
    istekaoJWT boolean;
    idTipaSesije char(1);
    idTipaKorisnika char(1);
    nazivOpstine text;
    idPotpisa uuid;
    trnZavodjenjaPotpisa timestamp;
    nazivInicijative Text;
    idInicijative integer;
    idPrilogaInicijative integer;
    rezultatJSON json;
    jwtHash2 text;
    jwtVrednost2 json;
    trnIstekaJWT2 timestamp;
    gradjanin2 RECORD;
    pozivnica text;
BEGIN

    idKorisnikaSavo = gen_random_uuid ();
    jwtVrednost = cast('{"a":"'|| cast(gen_random_uuid () as text) ||'"}' as json);
    jwtHash = cast(digest(cast(jwtVrednost as text), 'sha256') as text);
    trnIstekaJWT = cast (current_timestamp + interval '1 second' * jwtTimeoutSec as timestamp);
    -- inicijalizuj
    call ni.NITxIncNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, idKorisnikaSavo, 'М', 1986, '70106', 'Саво Манојловић');
    commit;
    call ni.NITxDajSesijuPoHash(jwtHash, prisutna, isteklaSesija, istekaoJWT, idTipaSesije, idTipaKorisnika);
    call ni.NITxIncDajProfil(jwtHash, idPola, godinaRodjenja, nazivOpstine, imePrezime, emailAdresa, biografija);
    call ni.NITxIncPodesiEmail(jwtHash,'savo@kreni-promeni.org');
    commit;
    call ni.NITxIncPodesiBiografiju(jwtHash,'Председник удружења Крени-Промени');
    commit;
    call ni.NITxIncDodajInicijativu(jwtHash, 'ОИ',
      'Забрана геолошких истраживања, експлоатације и прераде руде литијума',
      'У Закону о рударству и геолошким истраживањима:\\n- у члану 4. став 2. брише се тачка 5)\\n- у члану 4. став 2. тачки 7) после речи "друге минералне сировине" додају речи "осим руде литијума"\\n- после члана 4. додаје се члан 4а који гласи "Забрањују се примењена геолошка истраживања, експлоатација и прерада руде литијума у Републици Србији"',
      'savo@kreni-promeni.org',
      'Р', null, idInicijative);
    commit;
    call ni.NITxIncIzmeniInicijativu(jwtHash, idInicijative, 'КИ',
      'Забрана геолошких истраживања, експлоатације и прераде руда бора и литијума',
      'У Закону о рударству и геолошким истраживањима:\\n- у члану 4. став 2. брише се тачка 5)\\n- у члану 4. став 2. тачки 7) после речи "друге минералне сировине" додају речи "осим руде бора и литијума"\\n- после члана 4. додаје се члан 4а који гласи "Забрањују се примењена геолошка истраживања, експлоатација и прерада руде бора и литијума у Републици Србији"',
      'peticije@kreni-promeni.org',
      'Р', null);
    commit;
    call ni.NITxIncDodajPrilogInicijative(jwtHash, idInicijative, 
       'Пуни текст иницијативе',
       'https://www.docdroid.net/0vEHaIS/narodna-inicijativa-za-izmenu-i-dopunu-zakona-o-rudarstvu-i-geoloskim-istrazivanjima-14-2-2022.pdf', 
       1, 
       idPrilogaInicijative);
    commit;
    call ni.NITxIncIzmeniPrilogInicijative(jwtHash, idPrilogaInicijative, 
       'Пуни текст народне иницијативе',
       'https://www.docdroid.net/0vEHaIS/narodna-inicijativa-za-izmenu-i-dopunu-zakona-o-rudarstvu-i-geoloskim-istrazivanjima-14-2-2022-pdf', 
       1);
    commit;
    call ni.NITxIncObrisiPrilogInicijative(jwtHash, idPrilogaInicijative);
    commit;
    --
    call ni.NITxIncPripremiPozivnicu(jwtHash, idInicijative, pozivnica);
    commit;
    call ni.NITxIncPonistiPozivnicu(jwtHash, idInicijative);
    commit;
    --
    -- Uključi Zlatka
    call ni.NITxIncPripremiPozivnicu(jwtHash, idInicijative, pozivnica);
    commit;
    idKorisnikaZlatko = gen_random_uuid ();
    jwtVrednost2 = cast('{"a":"'|| cast(gen_random_uuid () as text) ||'"}' as json);
    jwtHash2 = cast(digest(cast(jwtVrednost2 as text), 'sha256') as text);
    trnIstekaJWT2 = cast (current_timestamp + interval '1 second' * jwtTimeoutSec as timestamp);
    -- inicijalizuj
    call ni.NITxIncNovaSesija(jwtHash2, jwtVrednost2, trnIstekaJWT2, idKorisnikaZlatko, 'М', 1965, '70106', 'Златко Кокановић');
    commit;
    call ni.NITxDajSesijuPoHash(jwtHash2, prisutna, isteklaSesija, istekaoJWT, idTipaSesije, idTipaKorisnika);
    call ni.NITxIncDajProfil(jwtHash2, idPola, godinaRodjenja, nazivOpstine, imePrezime, emailAdresa, biografija);
    call ni.NITxIncPodesiEmail(jwtHash2,'jadarnedamo@gmail.com');
    commit;
    call ni.NITxIncPodesiBiografiju(jwtHash2,'потпредседник удружења Не Дамо Јадар');
    commit;
    call ni.NITxIncIskoristiPozivnicu(jwtHash2, idInicijative, pozivnica);
    commit;
    --
    -- Uključi Ljiljanu
    call ni.NITxIncPripremiPozivnicu(jwtHash, idInicijative, pozivnica);
    commit;
    idKorisnikaLjiljana = gen_random_uuid ();
    jwtVrednost2 = cast('{"a":"'|| cast(gen_random_uuid () as text) ||'"}' as json);
    jwtHash2 = cast(digest(cast(jwtVrednost2 as text), 'sha256') as text);
    trnIstekaJWT2 = cast (current_timestamp + interval '1 second' * jwtTimeoutSec as timestamp);
    -- inicijalizuj
    call ni.NITxIncNovaSesija(jwtHash2, jwtVrednost2, trnIstekaJWT2, idKorisnikaLjiljana, 'Ж', 1975, '70106', 'Љиљана Браловић');
    commit;
    call ni.NITxDajSesijuPoHash(jwtHash2, prisutna, isteklaSesija, istekaoJWT, idTipaSesije, idTipaKorisnika);
    call ni.NITxIncDajProfil(jwtHash2, idPola, godinaRodjenja, nazivOpstine, imePrezime, emailAdresa, biografija);
    call ni.NITxIncPodesiEmail(jwtHash2,'suvoborskagreda@gmail.com');
    commit;
    call ni.NITxIncPodesiBiografiju(jwtHash2,'председница удружења Сувоборска Греда');
    commit;
    call ni.NITxIncIskoristiPozivnicu(jwtHash2, idInicijative, pozivnica);
    commit;
    --
    -- Uključi Marinu
    call ni.NITxIncPripremiPozivnicu(jwtHash, idInicijative, pozivnica);
    commit;
    idKorisnikaMarina = gen_random_uuid ();
    jwtVrednost2 = cast('{"a":"'|| cast(gen_random_uuid () as text) ||'"}' as json);
    jwtHash2 = cast(digest(cast(jwtVrednost2 as text), 'sha256') as text);
    trnIstekaJWT2 = cast (current_timestamp + interval '1 second' * jwtTimeoutSec as timestamp);
    -- inicijalizuj
    call ni.NITxIncNovaSesija(jwtHash2, jwtVrednost2, trnIstekaJWT2, idKorisnikaMarina, 'Ж', 1985, '70106', 'Марина Павлић');
    commit;
    call ni.NITxDajSesijuPoHash(jwtHash2, prisutna, isteklaSesija, istekaoJWT, idTipaSesije, idTipaKorisnika);
    call ni.NITxIncDajProfil(jwtHash2, idPola, godinaRodjenja, nazivOpstine, imePrezime, emailAdresa, biografija);
    call ni.NITxIncPodesiEmail(jwtHash2,'marina@kreni-promeni.org');
    commit;
    call ni.NITxIncPodesiBiografiju(jwtHash2,'Чланица Крени Промени');
    commit;
    call ni.NITxIncIskoristiPozivnicu(jwtHash2, idInicijative, pozivnica);
    commit;
    --
    -- Uključi Kristinu
    call ni.NITxIncPripremiPozivnicu(jwtHash, idInicijative, pozivnica);
    commit;
    idKorisnikaKristina = gen_random_uuid ();
    jwtVrednost2 = cast('{"a":"'|| cast(gen_random_uuid () as text) ||'"}' as json);
    jwtHash2 = cast(digest(cast(jwtVrednost2 as text), 'sha256') as text);
    trnIstekaJWT2 = cast (current_timestamp + interval '1 second' * jwtTimeoutSec as timestamp);
    -- inicijalizuj
    call ni.NITxIncNovaSesija(jwtHash2, jwtVrednost2, trnIstekaJWT2, idKorisnikaKristina, 'Ж', 1996, '70106', 'Кристина Урошевић');
    commit;
    call ni.NITxDajSesijuPoHash(jwtHash2, prisutna, isteklaSesija, istekaoJWT, idTipaSesije, idTipaKorisnika);
    call ni.NITxIncDajProfil(jwtHash2, idPola, godinaRodjenja, nazivOpstine, imePrezime, emailAdresa, biografija);
    call ni.NITxIncPodesiEmail(jwtHash2,'kristina@kreni-promeni.org');
    commit;
    call ni.NITxIncPodesiBiografiju(jwtHash2,'Чланица Крени Промени');
    commit;
    call ni.NITxIncIskoristiPozivnicu(jwtHash2, idInicijative, pozivnica);
    commit;
    --
    call ni.NITxIncPodnesiZahtev(jwtHash2, idInicijative);
    commit;
    call ni.NITxIncDetaljiInicijative(jwtHash, idInicijative, rezultatJSON);
    call ni.NITxIncListaPoFaziObrade(jwtHash, 'В', rezultatJSON);
END;
$$;
