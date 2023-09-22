/*
Created: 17/06/2023
Modified: 22/09/2023
Model: ModelPodatakaNarodneInicijative
Database: PostgreSQL 12
*/

-- liquibase formatted sql

-- changeset liquibase:rseni-tdm-model

set search_path=ni;


-- Create sequences section -------------------------------------------------

CREATE SEQUENCE SIDNIDnevnikPromena
  AS integer
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1
;

CREATE SEQUENCE SIDNIInicijativa
  AS integer
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1
;

CREATE SEQUENCE SIDNIPrilogInicijative
  AS integer
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1
;

-- Create tables section -------------------------------------------------

-- Table NIPol

CREATE TABLE NIPol
(
  IDNIPol Character(1) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIPol IS 'Pol potpisnika - podatak o ličnosti potreban za demografske statistike'
;
COMMENT ON COLUMN NIPol.IDNIPol IS 'Šifra pola - M ili Ž'
;
COMMENT ON COLUMN NIPol.Opis IS 'Opis pola - "Žena" ili "Muškarac"'
;
COMMENT ON COLUMN NIPol.Sortiranje IS 'Redosled za sortiranje'
;

ALTER TABLE NIPol ADD CONSTRAINT PK_NIPol PRIMARY KEY (IDNIPol)
;

-- Table NITipKorisnika

CREATE TABLE NITipKorisnika
(
  IDNITipKorisnika Character(1) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NITipKorisnika IS 'Tip korisnika - građanin (potpisnik narodne inicijative ili ovlašćeno lice organa vlati'
;
COMMENT ON COLUMN NITipKorisnika.IDNITipKorisnika IS 'Šifra tipa korisnika'
;
COMMENT ON COLUMN NITipKorisnika.Opis IS 'Opis tipa korisnika'
;
COMMENT ON COLUMN NITipKorisnika.Sortiranje IS 'Redosled sortiranja'
;

ALTER TABLE NITipKorisnika ADD CONSTRAINT PK_NITipKorisnika PRIMARY KEY (IDNITipKorisnika)
;

-- Table NINivoVlasti

CREATE TABLE NINivoVlasti
(
  IDNINivoVlasti Character(1) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NINivoVlasti IS 'Nivo vlasti kome se narodna inicijativa podnosi - republika, pokrajina ili opština'
;
COMMENT ON COLUMN NINivoVlasti.IDNINivoVlasti IS 'Šifra nivoa vlasti'
;
COMMENT ON COLUMN NINivoVlasti.Opis IS 'Opis nivoa vlasti'
;
COMMENT ON COLUMN NINivoVlasti.Sortiranje IS 'Redosled sortiranja'
;

ALTER TABLE NINivoVlasti ADD CONSTRAINT PK_NINivoVlasti PRIMARY KEY (IDNINivoVlasti)
;

-- Table NIFazaObrade

CREATE TABLE NIFazaObrade
(
  IDNIFazaObrade Character(1) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL,
  DozvoljeneSledeceFaze Text
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIFazaObrade IS 'Faza obrade (životnog ciklusa) narodne inicijative'
;
COMMENT ON COLUMN NIFazaObrade.IDNIFazaObrade IS 'Šifra faze obrade'
;
COMMENT ON COLUMN NIFazaObrade.Opis IS 'Opis faze obrade'
;
COMMENT ON COLUMN NIFazaObrade.Sortiranje IS 'Redosled sortiranja'
;
COMMENT ON COLUMN NIFazaObrade.DozvoljeneSledeceFaze IS 'Lista šifara faze obrade koje mogu da slede tekućoj fazi - za kontrolu unosa. Prazna lilsta znači da je faza obrade konačna.'
;

ALTER TABLE NIFazaObrade ADD CONSTRAINT PK_NIFazaObrade PRIMARY KEY (IDNIFazaObrade)
;

-- Table NIOpstina

CREATE TABLE NIOpstina
(
  IDNIOpstina Character(5) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL,
  BrojRegistrovanihGlasaca Integer NOT NULL,
  IDNIUpravniOkrug Character(3) NOT NULL,
  IDNIPokrajina Character(1)
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIOpstina IS 'Opština'
;
COMMENT ON COLUMN NIOpstina.IDNIOpstina IS 'Šifra opštine '
;
COMMENT ON COLUMN NIOpstina.Opis IS 'Naziv opštine'
;
COMMENT ON COLUMN NIOpstina.Sortiranje IS 'Redosled sortiranja'
;
COMMENT ON COLUMN NIOpstina.BrojRegistrovanihGlasaca IS 'Ukupan broj regisrovanih glasača na teritoriji opštine. Koristi se za procentualne statistike'
;

CREATE INDEX IX_UpravniOkrugOpstine ON NIOpstina (IDNIUpravniOkrug)
;

CREATE INDEX IX_PokrajinaOpstine ON NIOpstina (IDNIPokrajina)
;

ALTER TABLE NIOpstina ADD CONSTRAINT PK_NIOpstina PRIMARY KEY (IDNIOpstina)
;

-- Table NIUpravniOkrug

CREATE TABLE NIUpravniOkrug
(
  IDNIUpravniOkrug Character(3) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIUpravniOkrug IS 'Upravni okrug kome pripada grupa opština'
;
COMMENT ON COLUMN NIUpravniOkrug.IDNIUpravniOkrug IS 'Šifra upravnog okruga'
;
COMMENT ON COLUMN NIUpravniOkrug.Opis IS 'Naziv upravnog okruga'
;

ALTER TABLE NIUpravniOkrug ADD CONSTRAINT PK_NIUpravniOkrug PRIMARY KEY (IDNIUpravniOkrug)
;

-- Table NIPokrajina

CREATE TABLE NIPokrajina
(
  IDNIPokrajina Character(1) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIPokrajina IS 'Autonomna pokrajina'
;
COMMENT ON COLUMN NIPokrajina.IDNIPokrajina IS 'Šifra pokrajine'
;
COMMENT ON COLUMN NIPokrajina.Opis IS 'Ime pokrajine'
;
COMMENT ON COLUMN NIPokrajina.Sortiranje IS 'Redosled sortiranja'
;

ALTER TABLE NIPokrajina ADD CONSTRAINT PK_NIPokrajina PRIMARY KEY (IDNIPokrajina)
;

-- Table NIKorisnik

CREATE TABLE NIKorisnik
(
  IDNIKorisnik UUID NOT NULL,
  IDNITipKorisnika Character(1),
  TrnKreiranja Timestamp with time zone NOT NULL,
  TrnPoslednjeSesije Timestamp with time zone
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIKorisnik IS 'Korisnik sistema - građanin (potpisnik NI) ili ovlašćeno lice državnog organa'
;
COMMENT ON COLUMN NIKorisnik.IDNIKorisnik IS 'Šifra korisnika sistema'
;
COMMENT ON COLUMN NIKorisnik.TrnKreiranja IS 'Trenutak kada je kreiran korisnik - kada se korisnik prvi put prijavio na sistem ili kada je šalterska transakcija prijavila građanina kao potpisnika inicijative'
;
COMMENT ON COLUMN NIKorisnik.TrnPoslednjeSesije IS 'Trenutak poslednje uspešne sesije. Može biti prazan ako je građanin isključivo potpisivao peticije sa šaltera.'
;

CREATE INDEX IX_TipKorisnika ON NIKorisnik (IDNITipKorisnika)
;

ALTER TABLE NIKorisnik ADD CONSTRAINT PK_NIKorisnik PRIMARY KEY (IDNIKorisnik)
;

-- Table NIOvlascenoLice

CREATE TABLE NIOvlascenoLice
(
  IDNIOvlascenoLice UUID NOT NULL,
  IDNINivoVlasti Character(1),
  IDNIOpstina Character(5),
  IDNIPokrajina Character(1),
  ImeiPrezime Text NOT NULL,
  EmailAdresa Text NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIOvlascenoLice IS 'Ovlašćeno lice odgovarajućeg nivoa vlasti koje unosi odluke '
;
COMMENT ON COLUMN NIOvlascenoLice.ImeiPrezime IS 'Ime i prezime ovlašćenog lica preuzeto iz eID sistema'
;
COMMENT ON COLUMN NIOvlascenoLice.EmailAdresa IS 'Zvanična email adresa ovlašćenog lica preko koje članovi inicijativnog odbora mogu da stupe u kontakt'
;

CREATE INDEX IX_PredstavnikNivoaVlasti ON NIOvlascenoLice (IDNINivoVlasti)
;

CREATE INDEX IX_PredstavnikOpstine ON NIOvlascenoLice (IDNIOpstina)
;

CREATE INDEX IX_Relationship15 ON NIOvlascenoLice (IDNIPokrajina)
;

ALTER TABLE NIOvlascenoLice ADD CONSTRAINT PK_NIOvlascenoLice PRIMARY KEY (IDNIOvlascenoLice)
;

-- Table NIGradjanin

CREATE TABLE NIGradjanin
(
  IDNIGradjanin UUID NOT NULL,
  IDNIPol Character(1) NOT NULL,
  GodinaRodjenja Smallint NOT NULL,
  IDNIOpstina Character(5) NOT NULL,
  InicijatorovoIme Text,
  InicijatorovEmail Text,
  InicijatorovaBiografija Text
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIGradjanin IS 'Građanin - potpisnik inicijativa ili član inicijativnog odbora'
;
COMMENT ON COLUMN NIGradjanin.IDNIPol IS 'Pol građanina/građanke prenet iz matičnog registra za potrebe demografskih statistika potpisivanja'
;
COMMENT ON COLUMN NIGradjanin.GodinaRodjenja IS 'Godina rođenja građanina/građanke preneta iz matičnog registra za potrebe demografskih statistika potpisivanja'
;
COMMENT ON COLUMN NIGradjanin.IDNIOpstina IS 'Opština u kojoj je građanin/građanka upisana u birački spisak preneta iz biračkog spiska za potrebe geografskih statistika potpisivanja'
;
COMMENT ON COLUMN NIGradjanin.InicijatorovoIme IS 'Ime i prezime iz registra građana koje se prepisuje ovde samo ako je građanin bio jedan od članova inicijativnog odbora neke narodne inicijative'
;
COMMENT ON COLUMN NIGradjanin.InicijatorovEmail IS 'Email adresa za kontakt koja se traži od člana inicijativnog odbora'
;
COMMENT ON COLUMN NIGradjanin.InicijatorovaBiografija IS 'Kratka biografija koju unosi za sebe član inicijativnog odbora.'
;

CREATE INDEX IX_PolGradjanina ON NIGradjanin (IDNIPol)
;

CREATE INDEX IX_OpstinaBirSpiskaGradjanina ON NIGradjanin (IDNIOpstina)
;

ALTER TABLE NIGradjanin ADD CONSTRAINT PK_NIGradjanin PRIMARY KEY (IDNIGradjanin)
;

-- Table NISesija

CREATE TABLE NISesija
(
  IDNISesija Text NOT NULL,
  IDNITipSesije Character(1),
  IDNIKorisnik UUID NOT NULL,
  JWT Text NOT NULL,
  TrnPocetka Timestamp with time zone NOT NULL,
  TrnIstekaJWT Timestamp with time zone NOT NULL,
  TrnIstekaSesije Timestamp with time zone NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NISesija IS 'Sesija korisnika - podaci o sesiji uključujući JWT dobijen od eID servisa'
;
COMMENT ON COLUMN NISesija.IDNISesija IS 'Jedinistveni identifikator zapisa o sesiji. SHA-256 heš sračunat od vrednosti JWT'
;
COMMENT ON COLUMN NISesija.JWT IS 'JWT (Javascript Web Token) dodeljen od eID servisa kao dokaz prijave korisnika'
;
COMMENT ON COLUMN NISesija.TrnPocetka IS 'Trenutak početka / validacije sesije'
;
COMMENT ON COLUMN NISesija.TrnIstekaJWT IS 'Trenutak isteka JWT (iz potpisanog sadržaja JWT - exp claim)'
;
COMMENT ON COLUMN NISesija.TrnIstekaSesije IS 'Trenutak isteka sesije sa ovim JWT - trenutak API poziva + vreme isteka ili trenutak odjave (kad se implementira)'
;

CREATE INDEX IX_KorisnikSesije ON NISesija (IDNIKorisnik)
;

CREATE INDEX IX_TipSesije ON NISesija (IDNITipSesije)
;

ALTER TABLE NISesija ADD CONSTRAINT PK_NISesija PRIMARY KEY (IDNISesija)
;

-- Table NIInicijativa

CREATE TABLE NIInicijativa
(
  IDNIInicijativa Integer NOT NULL,
  IDNIGradjanin UUID NOT NULL,
  IDNITipInicijative Character(2) NOT NULL,
  NazivInicijative Text NOT NULL,
  TekstInicijative Text NOT NULL,
  TrnZahteva Timestamp with time zone NOT NULL,
  IDNIFazaObrade Character(1) NOT NULL,
  EmailZaKontakt Text NOT NULL,
  IDNINivoVlasti Character(1) NOT NULL,
  IDNIOpstina Character(5),
  IDNIPokrajina Character(1),
  PozivnicaSha256 Text,
  TrnPodnosenja Timestamp with time zone,
  TrnOdbijanjaZahteva Timestamp with time zone,
  RazlogOdbijanjaZahteva Text,
  DatumAktiviranja Date,
  DatumPokretanja Date,
  DatumOdluke Date,
  Prihvacena Boolean,
  BeleskaSaSednice Text
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIInicijativa IS 'Narodna inicijativa'
;
COMMENT ON COLUMN NIInicijativa.IDNIInicijativa IS 'Redni broj inicijative'
;
COMMENT ON COLUMN NIInicijativa.IDNIGradjanin IS 'Građanin koji je uneo zahtev za narodnu inicijativu i koji prvi počinje da poziva članove inicijativnog odbora (koji onda mogu sami da pozivaju dodatne članove'
;
COMMENT ON COLUMN NIInicijativa.NazivInicijative IS 'Tema (kratko ime) inicijative'
;
COMMENT ON COLUMN NIInicijativa.TekstInicijative IS 'Puni tekst inicijative'
;
COMMENT ON COLUMN NIInicijativa.TrnZahteva IS 'Trenutak kada je zahtev za narodnu inicijativu unesen u sistem'
;
COMMENT ON COLUMN NIInicijativa.EmailZaKontakt IS 'Email adresa preko koje nadležni organ vlasti ili građani mogu kontaktirati inicijativni odbor'
;
COMMENT ON COLUMN NIInicijativa.IDNIOpstina IS 'Opština čijoj se skupštini dostavlja inicijativa (ako je inicijativa na nivou opštine)'
;
COMMENT ON COLUMN NIInicijativa.IDNIPokrajina IS 'Pokrajina čijoj se skupštini dostavlja inicijativa (ako je inicijativa na nivou pokrajine)'
;
COMMENT ON COLUMN NIInicijativa.PozivnicaSha256 IS 'SHA-256 heš vrednost kriptografski slučajnog podatka koji se uz ID inicijative nalazi u pozivnici za novog člana inicijativnog odbora. Članovi se pozivaju jedan po jedan i jedna vrednost pozivnice može da se iskoristi samo za jednog člana. Kada novi član potvrdi članstvo ova vrednost se automatski briše, a može je obrisati i član IONI direktno. '
;
COMMENT ON COLUMN NIInicijativa.TrnPodnosenja IS 'Trenutak kada je inicijativni odbor finalizovao tekst inicijative in uključio sve članove i predao zahtev za inicijativu na proveru predsedniku odgovarajuće skupštine'
;
COMMENT ON COLUMN NIInicijativa.TrnOdbijanjaZahteva IS 'Trenutak kada je registrovano da je zahtev za odobravanje narodne inicijative odbijen od strane predsednika skupštine'
;
COMMENT ON COLUMN NIInicijativa.RazlogOdbijanjaZahteva IS 'Obrazloženje zašto je zahtev za narodnu inicijativu odbijen (npr. skupština je nenadležna)'
;
COMMENT ON COLUMN NIInicijativa.DatumAktiviranja IS 'Trenutak kada je inicijativa dobila odgovarajuću odluku i postala aktivna (za prijem potpisa)'
;
COMMENT ON COLUMN NIInicijativa.DatumPokretanja IS 'Datum kada je inicijativa pokrenuta (predata skupštini po prikupljanju kvalifikujućeg broja potpisa) - ili po isteku perioda prikupljanja potpisa, ili na zahtev inicijativnog odbora kada je sakupljen dovoljan broj potpisa.'
;
COMMENT ON COLUMN NIInicijativa.DatumOdluke IS 'Datum kada je skupština odlučivala o pokrenutoj narodnoj inicijativi'
;
COMMENT ON COLUMN NIInicijativa.Prihvacena IS 'Da li je inicijativa prihvaćena od strane skupštine'
;
COMMENT ON COLUMN NIInicijativa.BeleskaSaSednice IS 'Beleška o odlučivanju o pokrenutoj inicijativi na sednici skupštine (neobavezna)'
;

CREATE INDEX IX_NivoVlastiInicijative ON NIInicijativa (IDNINivoVlasti)
;

CREATE INDEX IX_FazaObradeInicijative ON NIInicijativa (IDNIFazaObrade)
;

CREATE INDEX IX_OpstinaInicijative ON NIInicijativa (IDNIOpstina)
;

CREATE INDEX IX_PokrajinaInicijative ON NIInicijativa (IDNIPokrajina)
;

CREATE INDEX IX_PokretacInicijative ON NIInicijativa (IDNIGradjanin)
;

CREATE INDEX IX_TipInicijative ON NIInicijativa (IDNITipInicijative)
;

ALTER TABLE NIInicijativa ADD CONSTRAINT PK_NIInicijativa PRIMARY KEY (IDNIInicijativa)
;

-- Table NIClanInicijativnogOdbora

CREATE TABLE NIClanInicijativnogOdbora
(
  IDNIGradjanin UUID NOT NULL,
  IDNIInicijativa Integer NOT NULL,
  TrnPrihvatanjaClanstva Timestamp with time zone NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON COLUMN NIClanInicijativnogOdbora.TrnPrihvatanjaClanstva IS 'Trenutak kada je građanin prihvatio poziv da bude član inicijativnog odbora'
;

ALTER TABLE NIClanInicijativnogOdbora ADD CONSTRAINT PK_NIClanInicijativnogOdbora PRIMARY KEY (IDNIGradjanin,IDNIInicijativa)
;

-- Table NIPotpisInicijative

CREATE TABLE NIPotpisInicijative
(
  IDNIInicijativa Integer NOT NULL,
  IDNIGradjanin UUID NOT NULL,
  Potpis UUID NOT NULL,
  TrnPotpisa Timestamp with time zone NOT NULL,
  PotpisNaSalteru Boolean NOT NULL,
  PodaciSaSalteraPotpisa Text
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIPotpisInicijative IS 'Potpis narodne inicijative od strane građanina'
;
COMMENT ON COLUMN NIPotpisInicijative.Potpis IS 'Jedinstveni identifikator potpisa - slučajni UUID'
;
COMMENT ON COLUMN NIPotpisInicijative.TrnPotpisa IS 'Trenutak kada je građanin potpisao inicijativu'
;
COMMENT ON COLUMN NIPotpisInicijative.PotpisNaSalteru IS 'Da li je potpis dat na šalteru (identifikacijom ličnim dokumentom) tj. da li je ne-elektronski'
;
COMMENT ON COLUMN NIPotpisInicijative.PodaciSaSalteraPotpisa IS 'Ako je potpis dat na šalteru, u ovom polju se nalaze podaci koji identifikuju lokaciju, šifru šalterskog radnika i slične informacije relevantne za nadzor'
;

ALTER TABLE NIPotpisInicijative ADD CONSTRAINT PK_NIPotpisInicijative PRIMARY KEY (IDNIInicijativa,IDNIGradjanin)
;

-- Table NIPrilogInicijative

CREATE TABLE NIPrilogInicijative
(
  IDNIPrilogInicijative Integer NOT NULL,
  IDNIInicijativa Integer NOT NULL,
  Sortiranje Numeric NOT NULL,
  NazivPriloga Text NOT NULL,
  URLPriloga Text NOT NULL,
  Obrisan Boolean NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIPrilogInicijative IS 'Veza ka javno dostupnom PDF fajlu priloženom od strane inicijativnog odbora koji sadrži dopunske detalje o inicijativi (opciono)'
;
COMMENT ON COLUMN NIPrilogInicijative.IDNIPrilogInicijative IS 'Sekvencni primarni ključ za ovaj slog'
;
COMMENT ON COLUMN NIPrilogInicijative.IDNIInicijativa IS 'Inicijativa na koju se odnosi prilog'
;
COMMENT ON COLUMN NIPrilogInicijative.NazivPriloga IS 'Naziv (kratak opis) priloga inicijative'
;
COMMENT ON COLUMN NIPrilogInicijative.URLPriloga IS 'URL do javno dostupnog PDF sa prilogom'
;
COMMENT ON COLUMN NIPrilogInicijative.Obrisan IS 'Da li je ovaj prilog obrisan u toku pripreme'
;

CREATE INDEX IX_InicijativaPriloga ON NIPrilogInicijative (IDNIInicijativa)
;

ALTER TABLE NIPrilogInicijative ADD CONSTRAINT PK_NIPrilogInicijative PRIMARY KEY (IDNIPrilogInicijative)
;

-- Table NIDnevnikPromena

CREATE TABLE NIDnevnikPromena
(
  IDNIDnevnikPromena Integer NOT NULL,
  TrnPromene Timestamp with time zone NOT NULL,
  DetaljiPromene Text NOT NULL,
  IDNIInicijativa Integer,
  IDNIFazaObrade Character(1),
  IDNISesija Text
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIDnevnikPromena IS 'Dnevnik promena inicijative'
;
COMMENT ON COLUMN NIDnevnikPromena.IDNIDnevnikPromena IS 'Sekvencni ključ dnevnika promena'
;
COMMENT ON COLUMN NIDnevnikPromena.DetaljiPromene IS 'Detalji promene'
;

CREATE INDEX IX_PromenjenaInicijativa ON NIDnevnikPromena (IDNIInicijativa)
;

CREATE INDEX IX_NovaFazaObrade ON NIDnevnikPromena (IDNIFazaObrade)
;

CREATE INDEX IX_SesijaPromene ON NIDnevnikPromena (IDNISesija)
;

ALTER TABLE NIDnevnikPromena ADD CONSTRAINT PK_NIDnevnikPromena PRIMARY KEY (IDNIDnevnikPromena)
;

-- Table NITipInicijative

CREATE TABLE NITipInicijative
(
  IDNITipInicijative Character(2) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NITipInicijative IS 'Tip inicijative - predlog novog zakona, predlog promene zakona, predlog referenduma, itd.'
;
COMMENT ON COLUMN NITipInicijative.IDNITipInicijative IS 'Šifra tipa inicijative'
;
COMMENT ON COLUMN NITipInicijative.Opis IS 'Opis tipa inicijative'
;
COMMENT ON COLUMN NITipInicijative.Sortiranje IS 'Redosled sortiranja tipova inicijative'
;

ALTER TABLE NITipInicijative ADD CONSTRAINT PK_NITipInicijative PRIMARY KEY (IDNITipInicijative)
;

-- Table NIParametar

CREATE TABLE NIParametar
(
  IDNIParametar Text NOT NULL,
  VrednostParametra Text,
  niapi Boolean NOT NULL,
  nipub Boolean NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NIParametar IS 'Parametri koje koriste baza i/ili servisi. U zavisnosti od oznake (niapi, nipub) parametar se dostavlja servisu kroz odgovarajuću funkciju.'
;
COMMENT ON COLUMN NIParametar.IDNIParametar IS 'Ime/kod parametra'
;
COMMENT ON COLUMN NIParametar.VrednostParametra IS 'Vrednost parametra. Može biti i prazna'
;
COMMENT ON COLUMN NIParametar.niapi IS 'Da li je parametar potreban API servisu?'
;
COMMENT ON COLUMN NIParametar.nipub IS 'Da li je parametar potreban servisu za objavljivanje?'
;

ALTER TABLE NIParametar ADD CONSTRAINT PK_NIParametar PRIMARY KEY (IDNIParametar)
;

-- Table NITipSesije

CREATE TABLE NITipSesije
(
  IDNITipSesije Character(1) NOT NULL,
  Opis Text NOT NULL,
  Sortiranje Numeric NOT NULL
)
WITH (
  autovacuum_enabled=true)
;
COMMENT ON TABLE NITipSesije IS 'Tip seslije - potpisnik, inicijator, ovlašćeno lice'
;
COMMENT ON COLUMN NITipSesije.IDNITipSesije IS 'Šifra tipa sesije'
;
COMMENT ON COLUMN NITipSesije.Opis IS 'Opis tipa sesije'
;
COMMENT ON COLUMN NITipSesije.Sortiranje IS 'Težina za sortiranje liste'
;

ALTER TABLE NITipSesije ADD CONSTRAINT PK_NITipSesije PRIMARY KEY (IDNITipSesije)
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE NIOpstina
  ADD CONSTRAINT FK_UpravniOkrugOpstine
    FOREIGN KEY (IDNIUpravniOkrug)
    REFERENCES NIUpravniOkrug (IDNIUpravniOkrug)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIOpstina
  ADD CONSTRAINT FK_PokrajinaOpstine
    FOREIGN KEY (IDNIPokrajina)
    REFERENCES NIPokrajina (IDNIPokrajina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIKorisnik
  ADD CONSTRAINT FK_TipKorisnika
    FOREIGN KEY (IDNITipKorisnika)
    REFERENCES NITipKorisnika (IDNITipKorisnika)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIOvlascenoLice
  ADD CONSTRAINT FK_OvlascenoLiceJeKorisnik
    FOREIGN KEY (IDNIOvlascenoLice)
    REFERENCES NIKorisnik (IDNIKorisnik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIGradjanin
  ADD CONSTRAINT FK_GradjaninJeKorisnik
    FOREIGN KEY (IDNIGradjanin)
    REFERENCES NIKorisnik (IDNIKorisnik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIGradjanin
  ADD CONSTRAINT FK_PolGradjanina
    FOREIGN KEY (IDNIPol)
    REFERENCES NIPol (IDNIPol)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIGradjanin
  ADD CONSTRAINT FK_OpstinaBirSpiskaGradjanina
    FOREIGN KEY (IDNIOpstina)
    REFERENCES NIOpstina (IDNIOpstina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIOvlascenoLice
  ADD CONSTRAINT FK_PredstavnikNivoaVlasti
    FOREIGN KEY (IDNINivoVlasti)
    REFERENCES NINivoVlasti (IDNINivoVlasti)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NISesija
  ADD CONSTRAINT FK_KorisnikSesije
    FOREIGN KEY (IDNIKorisnik)
    REFERENCES NIKorisnik (IDNIKorisnik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIInicijativa
  ADD CONSTRAINT FK_ZaNivoVlasti
    FOREIGN KEY (IDNINivoVlasti)
    REFERENCES NINivoVlasti (IDNINivoVlasti)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIInicijativa
  ADD CONSTRAINT FK_FazaObradeInicijative
    FOREIGN KEY (IDNIFazaObrade)
    REFERENCES NIFazaObrade (IDNIFazaObrade)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIInicijativa
  ADD CONSTRAINT FK_OpstinskaInicijativa
    FOREIGN KEY (IDNIOpstina)
    REFERENCES NIOpstina (IDNIOpstina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIInicijativa
  ADD CONSTRAINT FK_PokrajinskaInicijativa
    FOREIGN KEY (IDNIPokrajina)
    REFERENCES NIPokrajina (IDNIPokrajina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIOvlascenoLice
  ADD CONSTRAINT FK_PredstavnikOpstine
    FOREIGN KEY (IDNIOpstina)
    REFERENCES NIOpstina (IDNIOpstina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIOvlascenoLice
  ADD CONSTRAINT FK_PredstavnikPokrajine
    FOREIGN KEY (IDNIPokrajina)
    REFERENCES NIPokrajina (IDNIPokrajina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIClanInicijativnogOdbora
  ADD CONSTRAINT FK_GradjaninClanOdbora
    FOREIGN KEY (IDNIGradjanin)
    REFERENCES NIGradjanin (IDNIGradjanin)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIClanInicijativnogOdbora
  ADD CONSTRAINT FK_ClanOdboraInicijative
    FOREIGN KEY (IDNIInicijativa)
    REFERENCES NIInicijativa (IDNIInicijativa)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIPotpisInicijative
  ADD CONSTRAINT FK_PotpisanaInicijativa
    FOREIGN KEY (IDNIInicijativa)
    REFERENCES NIInicijativa (IDNIInicijativa)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIPotpisInicijative
  ADD CONSTRAINT FK_PotpisnikInicijative
    FOREIGN KEY (IDNIGradjanin)
    REFERENCES NIGradjanin (IDNIGradjanin)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIPrilogInicijative
  ADD CONSTRAINT FK_InicijativaPriloga
    FOREIGN KEY (IDNIInicijativa)
    REFERENCES NIInicijativa (IDNIInicijativa)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIDnevnikPromena
  ADD CONSTRAINT FK_PromenjenaInicijativa
    FOREIGN KEY (IDNIInicijativa)
    REFERENCES NIInicijativa (IDNIInicijativa)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIDnevnikPromena
  ADD CONSTRAINT FK_NovaFazaObrade
    FOREIGN KEY (IDNIFazaObrade)
    REFERENCES NIFazaObrade (IDNIFazaObrade)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NIDnevnikPromena
  ADD CONSTRAINT FK_SesijaPromene
    FOREIGN KEY (IDNISesija)
    REFERENCES NISesija (IDNISesija)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;
COMMENT ON CONSTRAINT FK_SesijaPromene ON NIDnevnikPromena IS 'Sesija korisnika u kojoj je izmena inicijative načinjena'
;

ALTER TABLE NIInicijativa
  ADD CONSTRAINT FK_PokretacInicijative
    FOREIGN KEY (IDNIGradjanin)
    REFERENCES NIGradjanin (IDNIGradjanin)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;
COMMENT ON CONSTRAINT FK_PokretacInicijative ON NIInicijativa IS 'Građanin koji i je uneo zahtev za narodnu inicijativu i koji je prvi član inicijativnog odbora'
;

ALTER TABLE NIInicijativa
  ADD CONSTRAINT FK_TipIncijative
    FOREIGN KEY (IDNITipInicijative)
    REFERENCES NITipInicijative (IDNITipInicijative)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE NISesija
  ADD CONSTRAINT FK_TipSesije
    FOREIGN KEY (IDNITipSesije)
    REFERENCES NITipSesije (IDNITipSesije)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

