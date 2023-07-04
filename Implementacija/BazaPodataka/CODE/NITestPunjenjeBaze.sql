CREATE OR REPLACE PROCEDURE ni.NITestPunjenjeBaze(IN pctbiraca int, IN jedinicaInicijativa int)
LANGUAGE plpgsql
AS $$
DECLARE
  opstina RECORD;
  gradjanin RECORD;
  korisnik UUID;
  godrodj  smallint;
  pol      char(1);
  brglasaca int;
  brglasova int;
  brutx     int;
  veltx     int;
  idInicijative int;
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
    select count(*)
      into brglasaca
      from NIGradjanin;
    veltx = 1000;
    FOR i IN 1..jedinicaInicijativa LOOP
        FOR i IN 1..700 LOOP
            call NITestInicijativa(idInicijative);
            brglasova = floor(random()*300);
            brutx = 0;
            FOR gradjanin IN SELECT IDNIGradjanin FROM NIGradjanin WHERE random() < (0.0+brglasova) / (0.0+brglasaca) LOOP
                INSERT INTO NIPotpisInicijative(IDNIInicijativa, IDNIGradjanin, Potpis, TrnPotpisa, PotpisNaSalteru) VALUES (idInicijative, gradjanin.IDNIGradjanin, gen_random_uuid (), now(), false);
                brutx=brutx+1;
                IF brutx > veltx THEN
                  brutx = 0;
                  COMMIT;
                END IF;
            END LOOP;
        END LOOP;
        FOR i IN 1..200 LOOP
            call NITestInicijativa(idInicijative);
            brglasova = 300 + floor(random()*700);
            brutx = 0;
            FOR gradjanin IN SELECT IDNIGradjanin FROM NIGradjanin WHERE random() < (0.0+brglasova) / (0.0+brglasaca) LOOP
                INSERT INTO NIPotpisInicijative(IDNIInicijativa, IDNIGradjanin, Potpis, TrnPotpisa, PotpisNaSalteru) VALUES (idInicijative, gradjanin.IDNIGradjanin, gen_random_uuid (), now(), false);
                brutx=brutx+1;
                IF brutx > veltx THEN
                  brutx = 0;
                  COMMIT;
                END IF;
            END LOOP;
            COMMIT;
        END LOOP;
        FOR i IN 1..70 LOOP
            call NITestInicijativa(idInicijative);
            brglasova = 1000 + floor(random()*2000);
            brutx = 0;
            FOR gradjanin IN SELECT IDNIGradjanin FROM NIGradjanin WHERE random() < (0.0+brglasova) / (0.0+brglasaca) LOOP
                INSERT INTO NIPotpisInicijative(IDNIInicijativa, IDNIGradjanin, Potpis, TrnPotpisa, PotpisNaSalteru) VALUES (idInicijative, gradjanin.IDNIGradjanin, gen_random_uuid (), now(), false);
                brutx=brutx+1;
                IF brutx > veltx THEN
                  brutx = 0;
                  COMMIT;
                END IF;
            END LOOP;
            COMMIT;
        END LOOP;
        FOR i IN 1..20 LOOP
            call NITestInicijativa(idInicijative);
            brglasova = 3000 + floor(random()*7000);
            brutx = 0;
            FOR gradjanin IN SELECT IDNIGradjanin FROM NIGradjanin WHERE random() < (0.0+brglasova) / (0.0+brglasaca) LOOP
                INSERT INTO NIPotpisInicijative(IDNIInicijativa, IDNIGradjanin, Potpis, TrnPotpisa, PotpisNaSalteru) VALUES (idInicijative, gradjanin.IDNIGradjanin, gen_random_uuid (), now(), false);
                brutx=brutx+1;
                IF brutx > veltx THEN
                  brutx = 0;
                  COMMIT;
                END IF;
            END LOOP;
            COMMIT;
        END LOOP;
        FOR i IN 1..7 LOOP
            call NITestInicijativa(idInicijative);
            brglasova = 10000 + floor(random()*20000);
            brutx = 0;
            FOR gradjanin IN SELECT IDNIGradjanin FROM NIGradjanin WHERE random() < (0.0+brglasova) / (0.0+brglasaca) LOOP
                INSERT INTO NIPotpisInicijative(IDNIInicijativa, IDNIGradjanin, Potpis, TrnPotpisa, PotpisNaSalteru) VALUES (idInicijative, gradjanin.IDNIGradjanin, gen_random_uuid (), now(), false);
                brutx=brutx+1;
                IF brutx > veltx THEN
                  brutx = 0;
                  COMMIT;
                END IF;
            END LOOP;
            COMMIT;
        END LOOP;
        FOR i IN 1..3 LOOP
            call NITestInicijativa(idInicijative);
            brglasova = 30000 + floor(random()*30000);
            brutx = 0;
            FOR gradjanin IN SELECT IDNIGradjanin FROM NIGradjanin WHERE random() < (0.0+brglasova) / (0.0+brglasaca) LOOP
                INSERT INTO NIPotpisInicijative(IDNIInicijativa, IDNIGradjanin, Potpis, TrnPotpisa, PotpisNaSalteru) VALUES (idInicijative, gradjanin.IDNIGradjanin, gen_random_uuid (), now(), false);
                brutx=brutx+1;
                IF brutx > veltx THEN
                  brutx = 0;
                  COMMIT;
                END IF;
            END LOOP;
            COMMIT;
        END LOOP;
    END LOOP;
END;
$$;
