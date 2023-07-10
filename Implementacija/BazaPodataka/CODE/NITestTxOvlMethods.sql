CREATE OR REPLACE PROCEDURE ni.NITestTxOvlMethods(
    IN  jwtTimeoutSec integer
)
LANGUAGE plpgsql
AS $$
DECLARE
    jwtHash text;
    jwtVrednost json;
    trnIstekaJWT timestamp;
    idKorisnika uuid;
    idNivoaVlasti text;
    idOpstine text;
    idPokrajine text;
    imePrezime text;
    emailAdresa text;
    prisutna boolean;
    isteklaSesija boolean;
    istekaoJWT boolean;
    idTipaSesije char(1);
    idTipaKorisnika char(1);
    nivoUprave text;
    opisJediniceUprave text;
    nazivOpstine text;
    idPotpisa uuid;
    trnZavodjenjaPotpisa timestamp;
    nazivInicijative Text;
    idInicijative integer;
    rezultat json;
BEGIN
    jwtVrednost = cast('{"a":"'|| cast(gen_random_uuid () as text) ||'"}' as json);
    jwtHash = cast(digest(cast(jwtVrednost as text), 'sha256') as text);
    trnIstekaJWT = cast (current_timestamp + interval '1 second' * jwtTimeoutSec as timestamp);
    -- inicijalizuj
    idKorisnika = gen_random_uuid ();
    idNivoaVlasti = 'Р';
    idOpstine = null;
    idPokrajine = null;
    imePrezime = 'Алекса Жуњић';
    emailAdresa = 'azunjic@gmail.com';
    call ni.NITxOvlNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, idKorisnika, idNivoaVlasti, idOpstine, idPokrajine, imePrezime, emailAdresa);
    commit;
    call ni.NITxDajSesijuPoHash(jwtHash, prisutna, isteklaSesija, istekaoJWT, idTipaSesije, idTipaKorisnika);
    call ni.NITxOvlDajProfil(jwtHash, imePrezime, emailAdresa, nivoUprave, opisJediniceUprave);
    -- podnesi jednu inicijativu na pregled
    SELECT min(i.IDNIInicijativa)
      INTO idInicijative
      FROM ni.NIInicijativa i
     WHERE i.IDNIFazaObrade = 'А';
    UPDATE ni.NIInicijativa
       SET IDNIFazaObrade = 'В'
     WHERE IDNIInicijativa = idInicijative;
    -- sad se vidi u listi za odobrenje
    call ni.NITxOvlListaZaOdobrenje(jwtHash, rezultat);
    call ni.NITxOvlOdobriInicijativu(jwtHash, idInicijative);
    UPDATE ni.NIInicijativa
       SET IDNIFazaObrade = 'П'
     WHERE IDNIInicijativa = idInicijative;
    call ni.NITxOvlListaZaIshod(jwtHash, rezultat);
    call ni.NITxOvlRegistrujPrihvacenuInicijativu(jwtHash, idInicijative, current_date, Cast('Тестна белешка са седнице' as text));
    call ni.NITxOvlDetaljiInicijative(jwtHash, idInicijative, rezultat);
END;
$$;
