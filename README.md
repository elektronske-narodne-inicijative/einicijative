# Elektronske Narodne Inicijative
## Probajte demo aplikaciju!
Radna verzija aplikacije koja pokriva funkcije potpisnika može da se vidi/proba [ovde](https://test-einicijativa.one/#/). Više detalja o stanju implementacije planovima možete naći na dnu ove stranice.

Sve ideje i komentari su dobrodošli - pišite na elektronske.narodne.inicijative@gmail.com.
## Čemu sve ovo?
U nadi da će Ministarstvo za državnu upravu i lokalnu samoupravu, Vladina kancelarija za informacione tehnologije i budući podugovarač, koji na tenderu za javne nabavke dobije da radi rešenje za Elektronske Narodne Inicijative, videti neku upotrebnu vrednost od priloženih ideja i tehničkog sadržaja, u ovom github repou i na Swagger hub-u ostavljamo sadržaj koji svako ko to poželi može koristiti u skladu sa [Apache 2.0 licencom](https://www.apache.org/licenses/LICENSE-2.0).

Ideje za sadržaj koji vidite ovde su razvijene u saradnji između Ivana Grujića i Gorana Vučkovića počevši od maja 2023. godine i dostavljene u formi dopisa Ministarstvu za državnu upravu i lokalnu samoupravu 12. juna 2023. od strane organizacije Transparentnost Srbija.

U paraleli su ideje tehnički razvijane, što je kao rezultat proizvelo tehničke sadržaje (tehničke specifikacije i izvorni kod) koji su dostupni kroz ovaj repo.
## Ciljevi proizvoda
Imajući u vidu kako domaća iskustva - "papirno" izvođene narodne inicijative, kao i strana, pre svega sajt [petition.parliament.uk](https://petition.parliament.uk/), tako i tekst [Zakona o referendumu i narodnoj inicijativi](https://www.paragraf.rs/propisi/zakon_o_referendumu_i_narodnoj_inicijativi.html) od 10. decembra 2021, ovo tehničko rešenje je projektovano tako da se ostvari nekoliko ključnih ciljeva:
- Omogući građanima da koriste svoje ustavno pravo pokretanja i učestvovanja u narodnim inicijativama na racionalan i efikasan način, oslanjajući se na postojeće resurse digitalne infrastrukture državnih organa Srbije
- Omogući da građani mogu jednostavno da kontrolišu svoje učešće u narodnim inicijativama
- Minimizuje rizike koji trenutno nastaju prikupljanjem podataka o ličnosti neophodnih za provere ne-elektronskih narodnih inicijativa
- Minimizuje verovatnoću tehničkih napada na sistem i povezane sisteme, kako elementima "zaštite u dubinu", tako i minimizovanjem vrednosti koju bi napadač dobio u slučaju uspešnog upada u sistem
## Ključne ideje na kojima je proizvod baziran
Ključne ideje na kojima je zasnovan ovaj predlog tehničkog rešenja su:
- Ako se identitet građanina verifikuje od strane sistema državnih organa u trenutku potpisivanja, prikupljanje uobičajenih podataka o ličnosti (ime, prezime, adresa stanovanja, JMBG) i njihovo dostavljanje inicijativnom odboru nije više svrsishodno, te ga ne treba ni raditi, u skladu sa principom srazmernosti iz članova 14 i 54 [Zakona o zaštiti podataka o ličnosti](https://www.paragraf.rs/propisi/zakon_o_zastiti_podataka_o_licnosti.html)
- Nema očiglednog razloga da se proces izvođenja narodnih inicijativa, počevši od pripreme zahteva i uključenja članova inicijativnog odbora, preko podnošenja i verifikacije zahteva od strane predsednika nadležne skupštine, prikupljanja potpisa - i na kraju pokretanja inicijative pred skupštinom i registracije odluke skupštine, *ne digitalizuje u celini* - i da se ono što bi bio tok papirnih dokumenata kroz institucije ne pretvori u prostu promenu stanja (faze obrade) elektronske narodne inicijative u sistemu.
- Građani koji nemaju tehničkih uslova da elektronske narodne inicijative potpišu elektronski treba da dobiju mogućnost da to urade identifikacijom ličnim dokumentom na šalterima pošta i opštinskih uprava - ovo se očekuje da bude efikasnije, jeftinije i skalabilnije nego postojeći ručni proces, koji zahteva verifikaciju od strane notara (uz sve ostale elemente rukovanja fizičkim dokumentom) - *prikupljanje, transport i konsolidacija bukvalno desetina hiljada pojedinačnih obrazaca, popunjenih od strane građana i overenih od strane ovlašćenih notara!*
- Građani treba da dobiju mogućnost da u elektronskoj formi (ili u papirnoj formi, na šalterima pošta i opštinske uprave) dobiju listu svih narodnih inicijativa koje su potpisali - i na taj način jednostavno kontrolišu da li je bilo koji učesnik u procesu zloupotrebio sistem
- Podaci o verifikovanim inicijativama treba da budu javno dostupni i lako pretraživi, uz efikasan prikaz geografskih (opština upisa u birački spisak) i demografskih (opseg starosti, pol) statistika potpisivanja.
## Faze obrade elektronske narodne inicijative
Opis rešenja počinjemo modelom stanja / faza obrade elektronskih narodnih inicijativa. Svaka elektronska narodna inicijativa u sistemu bi prolazila kroz faze obrade, vođena akcijama učesnika - član inicijativnog odbora podnese inicijativu na pregled (faza obrade promenjena na "Podneta"), ovlašćeno lice odgovarajućeg nivoa skupštine inicijativu potvrdi i time aktivira za potpisivanje (faza obrade promenjena na "Aktivna"), itd.

Inicijative postaju javno vidljive ako/kada uđu u fazu "Aktivna" - pre toga su vidljive samo inicijativnom odboru (dok su u pripremi) ili inicijativnom odboru i ovlašćenim licima odgovarajućeg nivoa državne uprave (na pregledu, odbijena).

Podnošenjem zahteva za narodnu incijativu članovi inicijativnog odbora gube mogućnost bilo kakvih promena sadržaja (osim označenih promena faze obrade - pokretanja ili povlačenja).

![ein-fazeobrade](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/75959deb-39e9-4e9e-8d60-907ab4f3fc0f)

## Korisničke priče
Opis budućeg tehničkog rešenja kroz opis pretpostavljenih zahteva ključnih tipova korisnika i razloga za te zahteve, koristeći pristup zvani "korisničke priče", dat je u [ovoj prezentaciji](https://docs.google.com/presentation/d/1cnEa4gFjD85ZG449C_NlCCojxXWHUtky/edit?usp=drive_link&ouid=100806157112222708210&rtpof=true&sd=true).

## Tehnička arhitektura
Sistem za elektronske narodne inicijative (u daljem tekstu eInicijativa/eInicijative ili einicijative.gov.rs) je zamišljen kao zasebna, "slabo spregnuta" (loosely coupled) elektronska usluga državnih organa koja se oslanja, što se tiče autentikacije korisnika (ovlašćenih lica organa javne uprave, članova inicijativnih odbora i potpisnika), na sistem [eid.gov.rs](https://eid.gov.rs/sr-Cyrl-RS/pocetna), koristeći [OpenID Connect protokol](https://openid.net/developers/how-connect-works/) i *JWT* koncept ([JSON Web Token](https://jwt.io/)), a što se tiče dostupnosti verifikovanih ličnih podataka na sistem [euprava.gov.rs](https://euprava.gov.rs/) (konzumirajući namenski novi API nazvan *u4niapi*). Kontekst eInicijativa sistema je prikazan na dijagramu ispod.

![ein-korisniciisistemi](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/03af8413-8c50-4d83-b988-a72a445a63bf)

eInicijativa daje uslugu šalterskim sistemima pošta i opštinskih uprava kroz odgovarajući API nazvan *niapi4s* (narodna inicijativa - API za šaltere) a takođe omogućava rad web aplikacije (zamišljene kao Single Page Web Application / SPA implementirane u nekoj od tehnologija kao React.js, Angular.js ili Vue.js) kombinacijom API-ja nazvanog niapi i mehanizma objavljivanja sadržaja, koji javne podatke periodično upisuje na javno dostupna mesta (poput npr. AWS S3 blob), odakle ih aplikacija čita. Unutrašnja struktura eInicijativa rešenja je prikazana na dijagramu ispod.

![ein-arhitektura](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/db958f85-b2fd-4bd6-853b-92cbf18a45c5)

Trajanje važnosti JWT bi zavisilo od namene tokena - za potpisivanje bi građanin dobijao token kratkog trajanja (npr. minut), dok bi za rad na unosu i podnošenju inicijative, kao inicijator / član inicijativnog odbora, dobijao dugotrajniji JWT. Cilj ovakvog pristupa je da se minimizuje opasnost od krađe/zloupotrebe izdatog tokena za potpisivanje inicijativa.

## Model domena (visokog nivoa)
Ključni koncepti / strukture podataka eInicijativa su prikazani na dijagramu ispod. 

![ein-modeldomena](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/53df3a4b-57bb-409d-a4e1-ee13832c6ce1)

Prilog inicijative je mogućnost da se jedan ili više PDF dokumenata prilože uz glavni tekst inicijative, ako je potrebno obezbediti detalje koji ne bi mogli da se opišu koncizno u glavnom tekstu inicijative. Formalni tekst narodne inicijative "konkretnog" tipa (detaljni opis novog zakona ili izmene zakona) bi skoro uvek bio u prilogu, dok bi glavni tekst inicijative bio lako čitljivi sažetak teksta u prilogu.

Zapis o promeni inicijative registruje ključne akcije nad inicijativom i korisnike koji su ih uradili.

Zamišljeno je da korisnici (građani i ovlašćena lica organa javne uprave) kao primarni identifikator dodeljen od portala eUprava za ovu namenu dobiju pseudonim visokog nivoa slučajnosti - konkretno [UUID verzije 4](https://en.wikipedia.org/wiki/Universally_unique_identifier#Version_4_(random)). Identifikatori potpisa inicijative koje generiše eInicijativa bi mogli biti istog tipa, dok bi identifikatori samih inicijativa, zbog ručnog unosa na šalterima, bili numerički identifikatori (sa ili bez kontrolne cifre). Gde kod je moguće (opštine, upravni okruzi, itd.) koristili bi se postojeći šifarski sistemi državnih organa / Republičkog zavoda za statistku.

Šalterski sistem bi u API-ju građane identifikovao JMBG-om (pročitanim sa lične karte ili ručno unetim), što bi eInicijativa "konvertovala" u UUID konsultaciom servisa eUprava. 
## Detaljni tehnički dizajn
### Model APIja
Na višem nivou detalja model API-ja je prikazan u Excel dokumentu dostupnom [ovde](https://docs.google.com/spreadsheets/d/1WypwdFRFTNrLOcRWk6TYy7qS2GphD4Ox/edit?usp=sharing&ouid=100806157112222708210&rtpof=true&sd=true). 

Opis logike koju se očekuje da implementiraju API metodi, očekivanih tipova poruka za centralizovani nadzorni trag i za sistem praćenja zdravlja usluge (health monitoring) se može naći [ovde](https://docs.google.com/spreadsheets/d/1xj-5adpQqYLbymp3qwKYa9Tunia3UFrd/edit?usp=sharing&ouid=100806157112222708210&rtpof=true&sd=true). Cilj ovog dokumenta je da ukaže na ključne korake obrade i njihovu "projekciju" na nadzorni dnevnik i sistem za praćenje zdravlja aplikacija i tako pomogne dizajnu nižeg nivoa i implementaciji. Pretpostavka je da se poruke za nadzorni trag dostavljaju syslog protokolom, u realnom vremenu, u SIEM sistem SOC-a državnih organa.

Detalji API-ja su pripremljeni u mašinski čitljivom OpenAPI 3.0 formatu i objavljeni na Swagger portalu u dva dela:
- API koji eUprava daje eInicijativi: [u4niapi](https://app.swaggerhub.com/apis/elektronske-narodne-inicijative/u4niapi/0.0.1#/)
- API koji eInicijativa daje šalterima (deo) ili javno: [niapi](https://app.swaggerhub.com/apis/elektronske-narodne-inicijative/niapi/0.0.1#/)

Iz OpenAPI specifikacije se lako može generisati kod za niz programskih jezika / alata, kao što se može videti na slici ispod.

![image](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/e63c1c6d-b65d-4be6-866d-37cf4b3fb396)

### Model dokumenata koji se objavljuju
U ovoj sekciji su povezane JSON šeme i primeri dokumenata koje bi proizvodila komponenta za objavljivanje. Ovi dokumenti se nalaze u ovom repou, na putanji *Dokumenti/Objavljivanje*.

| Tip dokumenta | Šema | Primer |
| --- | --- | --- |
| Nivoi vlasti | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-nivoi-vlasti.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-nivoi-vlasti.json) |
| Tipovi inicijativa | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-tipovi-inicijativa.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-tipovi-inicijativa.json) |
| Faze obrade | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-faze-obrade.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-faze-obrade.json) |
| Upravni okruzi | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-upravni-okruzi.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-upravni-okruzi.json) |
| Opštine | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-opstine.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-opstine.json) |
| Lista inicijativa (aktivne) | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-lista-inicijativa-aktivne.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-lista-inicijativa-aktivne.json) |
| Lista inicijativa (neaktivne) | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-lista-inicijativa-neaktivne.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-lista-inicijativa-neaktivne.json) |
| Detalji inicijative | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-inicijativa-{id}.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-inicijativa-{id}.json) |
### Podela odgovornosti između servisa srednjeg sloja i baze
U cilju povećanja kako efikasnosti, tako i bezbednosti (tkzv. odbrana u dubinu), u rešenju otvorenog koda na ovom repou će sprega srednjeg sloja i baze biti izvedena tako da srednji sloj komunicira sa bazom koristeći pozive procedura i funkcija u bazi na koje je dobio pristup, kao što je prikazano na dijagramu ispod.

![image](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/17429c58-ecc9-4a98-9d38-3dc2c941275d)

Ovo se može videti u implementaciji koda u bazi [ovde](https://github.com/elektronske-narodne-inicijative/einicijative/tree/main/Implementacija/BazaPodataka/CODE), kao i u skripti za dodelu prava pristupa rolama/korisnicima [ovde](https://github.com/elektronske-narodne-inicijative/einicijative/blob/main/Implementacija/BazaPodataka/CREATE/CrNIMWGrants.sql). Dokumentacija aplikativnih tipova grešaka (sqlcode) koje kod u bazi baca u izuzecima (RAISE EXCEPTION USING ERRORCODE ...) za ovaj projekat je [ovde](https://docs.google.com/spreadsheets/d/175thkJxzXbu9-bqGMlJKHblg7lWoGy1b/edit?usp=sharing&ouid=100806157112222708210&rtpof=true&sd=true).
### Model podataka
Dijagram tabela ispod je proizvod [Toad Data Modeler](https://www.quest.com/products/toad-data-modeler/) alata za modeliranje baza podataka. Model u ovom formatu, zajedno sa generisanim SQL skriptom, je sastavni deo izvornog koda rešenja. 

![ModelPodatakaNarodneInicijative](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/0a53b93c-b4c8-4c82-b69f-0430200bfe1c)

Tekući model je konfigurisan da generiše kod za besplatnu/open source [PostgreSQL](https://www.postgresql.org/) bazu, ali se relativno jednostavno može promeniti za druge podržane baze (Oracle, SQL Server, DB2, itd.). 

Sekcija ovog repoa sa izvornim kodom ("Implementacija") sadrži niz SQL skripti (jedna od njih je generisana iz alata za modeliranje) kojima se baza može instalirati koristeći alat za automatizaciju instalacija baza [Liquibase](https://www.liquibase.org/). XML dokument *index_changelog.xml* je indeks koji definiše redosled primene SQL skripti. Skripte očekuju da je na PostgreSQL instanci kreirana baza sa imenom "ni" u koju se instaliraju strukture podataka i inicijalno punjenje. Instalacija kreira korisnike *niapi* i *nipub* koje bi koristili odgovarajući servisi (opisani u sekciji *Tehnička ahitektura* iznad).

### Testno punjenje podacima
Na testnim okruženjima tipično je potrebno stvoriti određenu količinu sintetičkih podataka koji bi obezbedili da se baza podataka ponaša onako kako će se ponašati posle izvesnog vremena upotrebe na produkciji. Za ovu namenu je pripremljena nekolicina procedura i funkcija u bazi, sa procedurom <code>NITestPunjenjeBaze</code> koja objedinjuje proces i poziva ostale. 

Ova procedura se poziva sa dva parametra. Prvi parametar određuje koliko sintetičkih profila građana treba ubaciti, izraženo kroz procenat od ukupnog broja birača na opštini upisa u birački spisak. Drugi parametar kaže koliko "paketa" od 1000 narodnih inicijativa kreirati, pri čemu svaki "paket" sadrži sledeću strukturu potpisivanja:
- Prvih 700 narodnih inicijativa će imati između 0 i 299 potpisa (slučajno određen broj za svaku)
- Sledećih 200 narodnih inicijativa će imati između 300 i 999 potpisa
- SLedećih 70 će imati 1.000-2.999
- Sledećih 20 će imati 3.000-9.999
- Sledećih 7 će imati 10.000-29.999
- Konačno poslednjih 3 će imati 30.000-59.999

Sledeći poziv će kreirati nešto ispod milion građana i 10 hiljada inicijativa sa oko 7.7 miliona potpisa:

<code>call ni.NITestPunjenjeBaze(15,10);</code>
## Tekuće stanje implementacije rešenja, planovi
##### Poslednji put ažurirano: 05.10.2023
### Urađeno
U ovom trenutku su  baza i nipub servis manje-više kompletni (do na defekte, koji se čiste kako budu uočeni), a web sajt je u fazi kostura koji pokazuje podatke generisane od strane nipub servisa i implementira dinamičke funkcije koje se tiču akcija potpisnika (potpisivanje inicijative i gledanje liste potpisa. Servis niapi i dinamički deo sajta su trenutno u implementaciji. 

Što se tiče niapi implementacije, stanje je ovakvo:
- REST API je generisan iz OpenAPI specifikacije, prečišćen i integrisan
- Implementirana je veza sa bazom, uključujući i metode koji implementiraju poziv najvećeg dela potrebnih procedura u bazi
- Implementirani su logeri konfigurisani da šalju syslog protokolom podatke u SIEM (nadzorni dnevnik) i sistem za nadgledanje zdravlja infrastrukture
- Implementiran je REST klijent za u4niapi - predloženi novi API eUprava portala koji daje neophodne lične podatke ovom proizvodu
- Implementiran je simulator eUprava u4niapi koristeći [Wiremock](https://wiremock.org/) alat i postavljen na testni sajt [https://test-einicijativa.one](https://test-einicijativa.one) odakle se koristi u toku razvoja (konfiguracija je [ovde](https://github.com/elektronske-narodne-inicijative/einicijative/tree/main/Implementacija/Test/u4niapi-wiremock))
- Implementirani su niapi metodi za potpisnike, uključujući i metod za potpisivanje inicijative koji vraća base64 filtriranu potvrdu o potpisivanju koja je digitalno potpisani PDF dokument ([primer](https://github.com/elektronske-narodne-inicijative/einicijative/blob/main/Dokumenti/primer-potvrde-o-potpisu.pdf)).

### Planovi
Sada će funkcije za ovlašćena lica biti dodate u niapi, a onda i aplikaciju (njih je mnogo manje nego onih za inicijatore). Posle ovoga će u niapi i aplikaciju biti dodate funkcije za inicijatore.

Testno okruženje postavljeno [ovde](https://test-einicijativa.one/#/) (link sa vrha ove stranice) više nije statička aplikacija, nego kompletna postavka rešenja, sa bazom napunjenom testnim sadržajem kao što je opisano iznad (900k građana, 10k inicijativa i 7.9M potpisa), nipub i niapi servisima i sajtom. Ovo okruženje će biti dopunjavano kako razvoj bude tekao.

### Tajming
###### *Ovo je naravno sve hobi, koji radim uz dosta intenzivan glavni posao, tako da su datumi/periodi dole pre svega moja želja i lako mogu proklizati.

S obzirom na pozitivan razvoj integracije ključnih tehnologija i unutrašnjih elemenata niapi servisa, ima izgleda da se proizvod kompletira do kraja godine (ali i dalje sve može proklizati u slučaju nedostatka vremena).
