CREATE OR REPLACE PROCEDURE ni.NITestPunjenjeBaze(IN pctbiraca int)
LANGUAGE plpgsql
AS $$
DECLARE
  opstina RECORD;
  korisnik UUID;
  godrodj  smallint;
  pol      char(1);
BEGIN
    FOR i IN 1..5 LOOP
        korisnik = gen_random_uuid ();
        INSERT INTO NIKorisnik (IDNIKorisnik, IDNITipKorisnika, TrnKreiranja, TrnPoslednjeSesije) VALUES (korisnik,'О',now(), null);
        INSERT INTO NIOvlascenoLice 
         (IDNIOvlascenoLice, IDNINivoVlasti, IDNIOpstina, IDNIPokrajina, ImeiPrezime, EmailAdresa) 
        VALUES 
         (korisnik,'Р',null, null, cast(NITestZenskoIme() || ' ' || NITestPrezime() as text) ,cast('a' || substring(cast(korisnik as text),1,8) || '@gmail.com' as text));
    END LOOP;
    COMMIT;
    FOR opstina IN
       SELECT IDNIOpstina, BrojRegistrovanihGlasaca FROM NIOpstina ORDER BY sortiranje
    LOOP
        FOR i IN 1..3 LOOP
            korisnik = gen_random_uuid ();
            INSERT INTO NIKorisnik (IDNIKorisnik, IDNITipKorisnika, TrnKreiranja, TrnPoslednjeSesije) VALUES (korisnik,'О',now(), null);
            INSERT INTO NIOvlascenoLice 
             (IDNIOvlascenoLice, IDNINivoVlasti, IDNIOpstina, IDNIPokrajina, ImeiPrezime, EmailAdresa) 
            VALUES 
             (korisnik,'Р',null, null, cast(NITestZenskoIme() || ' ' || NITestPrezime() as text), cast('a' || substring(cast(korisnik as text),1,8) || '@gmail.com' as text));
        END LOOP;
        FOR i IN 1.. floor(opstina.BrojRegistrovanihGlasaca * pctbiraca / 100 / 10) LOOP
            FOR i IN 1..9 LOOP
                -- 9 od 10: gradjani / nisu inicijatori (opstina.IDNIOpstina)
                korisnik = gen_random_uuid ();
                godrodj = 1933 + floor(random()*72); -- 1933-2004
                case mod(godrodj,2)
                  when 1 then pol = 'Ж';
                  else pol = 'М';
                end case;
                INSERT INTO NIKorisnik (IDNIKorisnik, IDNITipKorisnika, TrnKreiranja, TrnPoslednjeSesije) VALUES (korisnik,'Г',now(), null);
                INSERT INTO NIGradjanin (IDNIGradjanin, IDNIPol, GodinaRodjenja, IDNIOpstina) values (korisnik,pol, godrodj,opstina.IDNIOpstina);
            END LOOP;
            -- 1 od 10: gradjani / inicijatori
            korisnik = gen_random_uuid ();
            godrodj = 1933 + floor(random()*72); -- 1933-2004
            INSERT INTO NIKorisnik (IDNIKorisnik, IDNITipKorisnika, TrnKreiranja, TrnPoslednjeSesije) VALUES (korisnik,'Г',now(), null);
            case mod(godrodj,2)
              when 1 then INSERT INTO NIGradjanin (IDNIGradjanin, IDNIPol, GodinaRodjenja, IDNIOpstina, InicijatorovoIme, InicijatorovEmail, InicijatorovaBiografija) values (korisnik,'Ж', godrodj,opstina.IDNIOpstina, cast(NITestZenskoIme() || ' ' || NITestPrezime() as text),cast('a' || substring(cast(korisnik as text),1,8) || '@gmail.com' as text), null);
              else INSERT INTO NIGradjanin (IDNIGradjanin, IDNIPol, GodinaRodjenja, IDNIOpstina, InicijatorovoIme, InicijatorovEmail, InicijatorovaBiografija) values (korisnik,'М', godrodj,opstina.IDNIOpstina, cast(NITestMuskoIme() || ' ' || NITestPrezime() as text),cast('a' || substring(cast(korisnik as text),1,8) || '@gmail.com' as text), null);
            end case;
            COMMIT; -- commit na svakih 30tak slogova
        END LOOP;
    END LOOP;
END;
$$;
