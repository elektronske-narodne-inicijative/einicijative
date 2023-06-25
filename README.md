# Elektronske Narodne Inicijative
## Cilj, namena, istorija
U nadi da će Ministarstvo za državnu upravu i lokalnu samoupravu, Vladina kancelarija za informacione tehnologije i budući podugovarač, koji najboljom ponudom dobije na tenderu za javne nabavke da radi rešenje za Elektronske Narodne Inicijative, videti neku upotrebnu vrednost od ove ideje i materijala, u ovom github repou i na Swagger hub-u ostavljamo sadržaj koji svako ko to poželi može koristiti u skladu sa [Apache 2.0 licencom](https://www.apache.org/licenses/LICENSE-2.0).

Materijal koji vidite ovde je spremljen u saradnji između Ivana Grujića i Gorana Vučkovića počevši od maja 2023. godine, a ključni elementi ideje su dostavljeni u formi dopisa Ministarstvu za državnu upravu i lokalnu samoupravu 12. juna 2023. od strane organizacije Transparentnost Srbija.

U paraleli je ideja tehnički razvijana, što je kao rezultat proizvelo materijale koji su dostupni kroz ovaj repo.
## Ciljevi
Imajući u vidu kako domaća iskustva - "papirno" izvođene narodne inicijative, kao i strana, pre svega sajt [petitions.parliament.uk](https://petition.parliament.uk/), tako i tekst Zakona o referendumu i narodnoj inicijativi od 10. decembra 2021, ovo tehničko rešenje je projektovano tako da se ostvari nekoliko ključnih ciljeva:
- Omogući građanima da koriste svoje ustavno pravo pokretanja i učestvovanja u narodnim inicijativama na racionalan i efikasan način, koristeći postojeće resurse digitalne infrastrukture državnih organa
- Omogući da građani mogu jednostavno da kontrolišu svoje učešće u narodnim inicijativama
- Minimizuje rizike koji trenutno nastaju prikupljanjem podataka o ličnosti neophodnih za provere ne-elektronskih narodnih inicijativa
- Minimizuje verovatnoću tehničkih napada na sistem i povezane sisteme, kako elementima "zaštite u dubinu", tako i minimizovanjem vrednosti koju bi napadač dobio u slučaju uspešnog upada u sistem
## Ključne ideje
Ključne ideje na kojima je zasnovan ovaj predlog tehničkog rešenja su:
- Ako se identitet građanina verifikuje od strane sistema državnih organa u trenutku potpisivanja, prikupljanje uobičajenih podataka o ličnosti (ime, prezime, adresa stanovanja, JMBG) nije više svrsishodno, te ga ne treba ni raditi
- Nema očiglednog razloga da se proces, počevši od pripreme zahteva i uključenja članova inicijativnog odbora, preko podnošenja i verifikacije zahteva od strane predsednika nadležne skupštine, prikupljanja potpisa - i na kraju pokretanja inicijative pred skupštinom i registracije odluke skupštine, *u celini ne digitalizuje* - i da se ono što bi bio tok papirnih dokumenata kroz institucije ne pretvori u prostu promenu stanja elektronske narodne inicijative u sistemu
- Građani koji nemaju tehničkih uslova da elektronske narodne inicijative potpišu elektronski treba da dobiju mogućnost da to urade identifikacijom ličnim dokumentom na šalterima pošta i opštinskih uprava - ovo se očekuje da bude efikasnije, jeftinije i skalabilnije nego postojeći ručni proces koji zahteva verifikaciju od strane notara
- Građani treba da dobiju mogućnost da u elektronskoj formi (ili u papirnoj formi na šalterima pošta i opštinske uprave) dobiju listu svih narodnih inicijativa koje su potpisali - i na taj način jednostavno kontrolišu da li je bilo koji učesnik u procesu zloupotrebio sistem
- Podaci o verifikovanim inicijativama treba da budu javno dostupni i lako pretraživi, uz efikasan prikaz geografskih (opština upisa u birački spisak) i demografskih (opseg starosti, pol) statistika.
## Faze obrade elektronske narodne inicijative
Opis rešenja počinjemo modelom faza obrade elektronskih narodnih inicijativa. Svaka elektronska narodna inicijativa u sistemu bi bila u jednoj od ovih faza. Inicijative postaju javno vidljive ako uđu u fazu "Aktivna" - pre toga su vidljive samo inicijativnom odboru (u pripremi) ili inicijativnom odboru i ovlašćenim licima odgovarajućeg nivoa državne uprave (na pregledu, odbijena).

![ein-fazeobrade](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/75959deb-39e9-4e9e-8d60-907ab4f3fc0f)

## Tehnička arhitektura
Sistem za elektronske narodne inicijative (u daljem tekstu eInicijative ili einicijative.gov.rs) je zamišljen kao zasebna, "slabo spregnuta" (loosely coupled) elektronska usluga državnih organa koja se oslanja, što se tiče autentikacije korisnika (ovlašćenih lica organa javne uprave, članova inicijativnih odbora i potpisnika), na sistem eid.gov.rs, koristeći *JWT* koncept (Javascript Web Token), a što se tiče dostupnosti verifikovanih ličnih podataka na sistem euprava.gov.rs (konzumirajući namenski novi API nazvan *u4niapi*). Kontekst eInicijativa sistema je prikazan na dijagramu ispod.

![ein-korisniciisistemi](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/03af8413-8c50-4d83-b988-a72a445a63bf)

eInicijativa daje uslugu šalterskim sistemima pošta i opštinskih uprava kroz odgovarajući API nazvan *niapi4s* (narodna inicijativa - API za šaltere) a takođe omogućava rad web aplikacije (zamišljene kao Single Page Web Application / SPA implementirane u nekoj od tehnologija kao React.js, Angular.js ili Vue.js) kombinacijom API-ja nazvanog niapi i mehanizma objavljivanja sadržaja koji javne podatke periodično upisuju na javno dostupna mesta (poput npr. AWS S3 blob) odakle ih aplikacija čita. Unutrašnja struktura eInicijativa rešenja je prikazana na dijagramu ispod.

![ein-arhitektura](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/db958f85-b2fd-4bd6-853b-92cbf18a45c5)

## Model domena (visokog nivoa)
Ključni koncepti / strukture podataka eInicijativa su prikazani na dijagramu ispod. 

![ein-modeldomena](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/53df3a4b-57bb-409d-a4e1-ee13832c6ce1)

Prilog inicijative je mogućnost da se jedan ili više PDF dokumenata prilože uz glavni tekst inicijative, ako je potrebno obezbediti detalje koji ne bi mogli da se opišu koncizno u glavnom tekstu inicijative.
Zapis o promeni inicijative registruje ključne akcije nad inicijativom i korisnike koji su ih uradili.

Zamišljeno je da korisnici (građani i ovlašćena lica organa javne uprave) kao primarni identifikator dodeljen od portala eUprava za ovu namenu dobiju pseudonim visokog nivoa slučajnosti - konkretno UUID verzije 4. Identifikatori potpisa inicijative koje generiše eInicijativa bi mogli biti istog tipa, dok bi identifikatori samih inicijativa, zbog ručnog unosa na šalterima, bili numerički identifikatori (sa ili bez kontrolne cifre). Gde kod je moguće (opštine, upravni okruzi, itd.) koristili bi se postojeći šifarski sistemi državnih organa / Republičkog zavoda za statistku.

Šalterski sistem bi u API-ju građane identifikovao JMBG-om (pročitanim sa lične karte ili ručno unetim), što bi eInicijativa "konvertovala" u UUID konsultaciom servisa eUprava. 
## Detaljni tehnički dizajn
### Model podataka
Dijagram tabela ispod je proizvod [Toad Data Modeler](https://www.quest.com/products/toad-data-modeler/) alata za modeliranje baza podataka koji je sastavni deo izvornog koda rešenja. 

![ModelPodatakaNarodneInicijative](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/f3a004ec-62ae-411b-a1ff-b05b0c170bae)

Tekući model je konfigurisan da generiše kod za besplatnu/open source PostgreSQL bazu, ali se relativno jednostavno može promeniti za druge podržane baze (Oracle, SQL Server, DB2, itd.).
### Model APIja
Na višem nivou detalja model API-ja je prikazan u Excel dokumentu dostupnom [ovde](https://docs.google.com/spreadsheets/d/1WypwdFRFTNrLOcRWk6TYy7qS2GphD4Ox/edit?usp=sharing&ouid=100806157112222708210&rtpof=true&sd=true). 

Detalji API-ja su pripremljeni u mašinski čitljivom OpenAPI 3.0 formatu i objavljeni na Swagger portalu u dva dela:
- API koji eUprava daje eInicijativi: [u4niAPI](https://app.swaggerhub.com/apis/elektronske-narodne-inicijative/u4niapi/0.0.1#/)
- API koji eUPrava daje šalterima (deo) ili javno: [niapi](https://app.swaggerhub.com/apis/elektronske-narodne-inicijative/niapi/0.0.1#/)

Iz OpenAPI specifikacije se lako može generisati kod za niz programskih jezika / alata, kao što se može videti na slici ispod.

![image](https://github.com/elektronske-narodne-inicijative/einicijative/assets/137355033/e63c1c6d-b65d-4be6-866d-37cf4b3fb396)

### Model dokumenata koji se objavljuju
U ovoj sekciji su povezane JSON šeme i primeri dokumenata koje bi proizvodila komponenta za objavljivanje. Ovi dokumenti se nalaze u ovom repou, na putanji *Dokumenti/Objavljivanje*.

| Tip dokumenta | Šema | Primer |
| --- | --- | --- |
| Nivoi vlasti | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-nivoi-vlasti.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-nivoi-vlasti.json) |
| Tipovi inicijativa | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-tipovi-inicijativa.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-tipovi-inicijativa.json) |
| Faze obrade | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-faze-obrade.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-faze-obrade.json) |
| Opštine | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-opstine.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-opstine.json) |
| Upravni okruzi | [Šema](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Seme/sema-upravni-okruzi.json) | [Primer](https://raw.githubusercontent.com/elektronske-narodne-inicijative/einicijative/main/Dokumenti/Objavljivanje/Primeri/primer-upravni-okruzi.json) |

(*radovi u toku*)

