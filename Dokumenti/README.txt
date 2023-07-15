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

Sledeće što možete da uradite je da pokrenete testove procedura koje podržavaju API metode za inicijatore:

call ni.NITestTxIncMethods(70);

Rezultate u bazi možete da vidite pregledom odgovarajućih tabela:

select * from niinicijativa;
select * from nipriloginicijative;
select * from niclaninicijativnogodbora;
select * from ni.nidnevnikpromena order by idnidnevnikpromena desc;

Da biste napunili bazu ozbiljnijom količinim podataka koristite proceduru NITestPunjenjeBaze - ona je opisana u glavnom README fajlu projekta. Primer dole popunjava 2% glasača i 2x1000 inicijativa:

call ni.NITestPunjenjeBaze(2,2); 

Za sledeći korak vam je potreban jedan ID građanina - to možete da dobijete iz liste koju vraća ovaj upit:

select * from nigradjanin;

UUID koji ste pronašli zamenite umesto primera ispod i tako pustite testove potpisnika - prvi će ubaciti 31 potpis i onda pući pošto je istekla sesija za potpisivanje (30 je od 30 sekundi), a drugi će ubaciti 2.000 potpisa (ako je testno punjenje sa 2x1000 inicijativa).

delete from nipotpisinicijative where idnigradjanin = cast('59239ea0-9767-46ad-8930-0f901431c177' as uuid);
call ni.NITestTxPtpMethods(cast('59239ea0-9767-46ad-8930-0f901431c177' as uuid), 30, 1.0);

delete from nipotpisinicijative where idnigradjanin = cast('59239ea0-9767-46ad-8930-0f901431c177' as uuid);
call ni.NITestTxPtpMethods(cast('59239ea0-9767-46ad-8930-0f901431c177' as uuid), 70, 0.001);

delete from nipotpisinicijative where idnigradjanin = cast('59239ea0-9767-46ad-8930-0f901431c177' as uuid);

Konačno testove API metoda ovlašćenih lica možete startovati na sledeći način (4 seta testova):

call ni.NITestTxOvlMethods(600);
call ni.NITestTxOvlMethods(600);
call ni.NITestTxOvlMethods(600);
call ni.NITestTxOvlMethods(600);

Rezultat je da će 4 inicijative biti prebačene u konačno stanje (unet ishod), što možete proveriti sledećim upitom:

select * from niinicijativa where idnifazaobrade='К';

