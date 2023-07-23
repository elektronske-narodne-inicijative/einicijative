======================================
eInicijativa: Instalacija i testiranje
======================================

Ovo je kratko uputstvo za instalaciju i testiranje (uključujući i instalaciju i podešavanje Liquibase). Pretpostavlja se da ćete koristiti Docker za instalaciju baze. Ja ceo ovaj ciklus instalacije i brisanja radim više puta u jednom setu promena (obično gledam da dovršim i testiram promene u jednom danu). Sigurno bi ovo moglo još bolje da se automatizuje, ali ovo meni uglavnom završava posao (5-7 minuta po ciklusu uključujući i 2-3 minuta za "call ni.NITestPunjenjeBaze(2,2)".

Pošto je projekat i dalje u vrlo razvojnoj fazi, trenutno još ne primenjujem formalno verzionisanje, ali se trudim da ono što čekinujem radi. 

Unapred se izvinjavam na neprijatnostima bilo koje vrste u ovoj fazi.

Vučko

------------------------------------
1. INSTALACIJA

1.1. Baza podataka

Za potrebe testiranja bazu projekta eInicijativa je najlakše instalirati u PostgreSQL bazu koja je startovana na vašem računaru kao Docker kontejner Primer startovanja kontejnera sa najnovijom verzijom PostgreSQL baze izgleda ovako:

docker run --name eintestdb -p 5432:5432 -e POSTGRES_PASSWORD=VasaNajtajnijaLozinkaPoIzboru -d postgres

Jednom kada startujete kontejner, potrebno je da kreirate bazu pod imenom "ni". To je najlakše uraditi tako što exec-ujete u kontejner (sa komandne linije ili iz Docker Destopa) i onda uradite sledeće (promptovi na početku linije su za lakše snalaženje i ne unosite ih):

C:/...>docker exec -it eintestdb /bin/bash
...# su - postgres
...$ psql
...
postgres=# create database ni with template = template0 encoding = 'UTF8';
postgres=# exit

Sledeći korak je instalacija aplikacionih SQL skripti. za ovo je potrebno da sa https://www.liquibase.com/download skinete zip arhivu za Windows (ili Linux, ako ga koristite) i raspakujete je na nekom zgodnom mestu, npr. u folderu pored onoga gde je projekat (da ne bi Liquibase postao deo projekta). U okviru paketa se nalazi README.txt fajl gde se mogu videti ostali detalji - meni je najzgodnije da arhivu raspakujem u folder koji se zove Liquibase a onda na nivou gde je tajl folder izvučem fajl liquibase.properties u kome podesim ključne informacije (nalaze se na početku fajla, uz dosta komentara):

changeLogFile=[relativna ili apsolutna putanja do repoa]/einicijative/Implementacija/BazaPodataka/index_changelog.xml
liquibase.command.url=jdbc:postgresql://localhost:5432/ni
liquibase.command.username: postgres
liquibase.command.password: [lozinka koju ste stavili pri startovanju kontejnera]

Aplikacija se onda instalira ovakvom komandom:

C:/...>Liquibase\liquibase.bat update

Ovo će sa tekućeg foldera pokupiti liquibase.properties i iz njega pročitati informacije gore i instalirati bazu - videćete koje je sve fajlove primenio i da li ima bilo kakvih grešaka (ne bi trebalo).

1.1. Servis za objavljivanje nipub

Izvorni kod je podešen za rad sa IntelliJ IDEA. Idealno bi bilo (ako koristite Windows) da kreirate foldere sa imenima "C:/TEMP/einicijativa/podaci/zajednicko/" i "C:/TEMP/einicijativa/podaci/inicijative/" - inače podesite podatke na vaše lokalne foldere i trudite se da ne čekinujete ovu izmenu.

Da bi servis ispravno objavljivao ćirilicu potrebno je da podesite VM options projekta (Run -> Edit Configurations) tako što dodate "-Dfile.encoding=UTF-8".


------------------------------------
2. TESTIRANJE

2.1. Baza podataka

Kada su baza i aplikacija instalirane ja obično prvo proverim da li sve radi kao što je i ranije radilo, pre nego što nastavim razvoj. Deo procedura i funkcija u bazi je napravljen za potrebe testiranja. Evo primera kratkog "regresionog" testa:

Uradite exec u kontejner, su na postgres korisnika i zakačite se na ni bazu komandom:

psql -d ni

Kada ovo uradite preporučujem sledeće komande, koje će postaviti tekuću šemu, aktivirati dopunske detalje u prikazu grešaka i merenje vremena izvršavanja:

set search_path=ni;
\set VERBOSITY verbose
\timing

Sledeće što možete da uradite je da pokrenete paket testova (minimalna varijanta inicijalnog punjenjenja je sa ni.NITestPunjenjeBaze(1,1)):

do $$ 
declare
    idGradjanina uuid;
begin
    call ni.NITestPunjenjeBaze(1,1);
    select cast(min(cast(IDNIGradjanin as text)) as uuid)
      into idGradjanina
      from ni.nigradjanin;
    delete from nipotpisinicijative where idnigradjanin = idGradjanina;
    call ni.NITestTxPtpMethods(idGradjanina, 70, 0.001);
    delete from nipotpisinicijative where idnigradjanin = idGradjanina;
    call ni.NITestTxSltMethods(idGradjanina, 0.001);
    call ni.NITestTxIncMethods(70);
    call ni.NITestTxOvlMethods(600);
    call ni.NITestTxOvlMethods(600);
    call ni.NITestTxOvlMethods(600);
    call ni.NITestTxOvlMethods(600);
end $$;


2.2. Servis za objavljivanje

Kada se paket testova iz prethodne tačke izvrši možete da startujete nipub i on će objaviti sve dokumente (zajedničke plus inicijative upravo kreirane test skriptom).

Parametri za pokretanje paketa se nalaze u application.properties fajlu, a parametar koji određuje period aktivnosti inicijative koja se objavljuje je u bazi, u tabeli NIParametar i podešen je na 600 sekundi (10 minuta).
