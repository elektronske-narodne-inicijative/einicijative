CREATE OR REPLACE PROCEDURE ni.NITestTxIncMethods(
    IN  idKorisnikaSavo uuid,
    IN  jwtTimeoutSec integer
)
LANGUAGE plpgsql
AS $$
DECLARE
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
BEGIN
    SELECT *
      INTO STRICT gradjanin
      FROM ni.NIGradjanin g
     WHERE g.IDNIGradjanin = idKorisnikaSavo;
    jwtVrednost = cast('{"a":"'|| cast(gen_random_uuid () as text) ||'"}' as json);
    jwtHash = cast(digest(cast(jwtVrednost as text), 'sha256') as text);
    trnIstekaJWT = cast (current_timestamp + interval '1 second' * jwtTimeoutSec as timestamp);
    -- inicijalizuj
    call ni.NITxIncNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, gradjanin.IDNIGradjanin, gradjanin.IDNIPol, gradjanin.GodinaRodjenja, gradjanin.IDNIOpstina, 'Саво Манојловић');
    commit;
    call ni.NITxDajSesijuPoHash(jwtHash, prisutna, isteklaSesija, istekaoJWT, idTipaSesije, idTipaKorisnika);
    call ni.NITxIncDajProfil(jwtHash, idPola, godinaRodjenja, nazivOpstine, imePrezime, emailAdresa, biografija);
    call ni.NITxIncPodesiEmail(jwtHash,'savo@kreni-promeni.org');
    commit;
    call ni.NITxIncPodesiEmail(jwtHash,'Председник удружења Крени-Промени');
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
    call ni.NITxIncDetaljiInicijative(jwtHash, idInicijative, rezultatJSON);
    call ni.NITxIncListaPoFaziObrade(jwtHash, 'У', rezultatJSON);
END;
$$;
