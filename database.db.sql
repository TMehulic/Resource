BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "course" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL UNIQUE,
	"description"	TEXT,
	"ects"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "titleInfo" (
	"id"	INTEGER,
	"title"	TEXT,
	"personId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "courseProfessor" (
	"id"	INTEGER,
	"courseId"	INTEGER,
	"personId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("courseId") REFERENCES "course"("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "courseStudent" (
	"id"	INTEGER,
	"courseId"	INTEGER,
	"personId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("courseId") REFERENCES "course"("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "person" (
	"id"	INTEGER,
	"lastName"	TEXT,
	"firstName"	TEXT,
	"fathersName"	TEXT,
	"birthPlace"	TEXT,
	"jmbg"	TEXT,
	"phone"	TEXT,
	"email"	INTEGER NOT NULL UNIQUE,
	"image"	TEXT,
	"birthDate"	TEXT,
	"gender"	TEXT,
	"student"	INTEGER,
	"professor"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "educationInfo" (
	"id"	INTEGER,
	"degree"	TEXT,
	"cycle"	INTEGER,
	"year"	INTEGER,
	"index"	INTEGER,
	"personId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "residenceInfo" (
	"id"	INTEGER,
	"adress"	TEXT,
	"canton"	TEXT,
	"county"	TEXT,
	"personId"	INTEGER,
	FOREIGN KEY("personId") REFERENCES "person"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "courseNews" (
	"id"	INTEGER,
	"news"	TEXT,
	"date"	TEXT,
	"courseId"	INTEGER,
	FOREIGN KEY("courseId") REFERENCES "course"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "user" (
	"id"	INTEGER,
	"personId"	INTEGER,
	"email"	TEXT UNIQUE,
	"password"	TEXT,
	FOREIGN KEY("personId") REFERENCES "person"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "courseMaterials" (
	"id"	INTEGER,
	"title"	TEXT,
	"path"	TEXT,
	"courseId"	INTEGER,
	FOREIGN KEY("courseId") REFERENCES "course"("id"),
	PRIMARY KEY("id")
);
INSERT INTO "course" VALUES (1,'Numerički algoritmi','Cilj ovog kursa je upoznavanje sa problemima vezanim za implementaciju računanja sa realnim brojevima na računaru, te dizajn brzih, tačnih i pouzdanih algoritama za rješavanje tipičnih problema numeričke prirode (računanja sa matricama, rješavanje jednačina i sistema jednačina, interpolacija, aproksimacija, numeričko diferenciranje i integriranje, numeričko rješavanje diferencijalnih jednačina, brza Fourierova transformacija).',5);
INSERT INTO "course" VALUES (2,'Algoritmi i strukture podataka','Cilj kursa je sticanja koherentnog znanja o tehnikama za implementiranje algoritama i strukturama podataka. U isto vrijeme kurs pruža studentima mogućnost da unaprijede svoje programersko znanje prilikom razvoja i primjene raznih algoritama u okviru konkretnih programskih rješenja.',5);
INSERT INTO "course" VALUES (3,'Diskretna matematika','Cilj kursa je da obezbijedi studentima solidne teorijske osnove kako bi na sistematičan način mogli rješavati matematske probleme informatičkog karaktera, a koji su vezani za elementarnu teoriju brojeva, kombinatoriku, elementarnu teoriju vjerovatnoće, teoriju informacija, teoriju grafova i teoriju diskretnih sistema.',5);
INSERT INTO "course" VALUES (4,'Logički dizajn
','Svrha ovog predmeta je uvođenje studenta u principe logičkog dizajna i projektovanja osnovnih komponenti digitalnog računara tradicionalnim i savremenim metodama. U hijerarhiji apstrakcija počinje od logičkih kola i završava sa jednostavnim ali funkcionalnim mikroprogramiranim procesorom (centralnom procesnom jedinicom) i njegovim mašinskim jezikom. Studenti treba da razumiju principe na kojima rade jednostavni računarski sistemi, kao i prednosti i nedostatke hardverskog i softverskog rješavanja problema. Preduslovi za razumjevanje gradiva ovog kursa su poznavanje osnova digitalne (prekidačke) elektronike kao i osnova računarstva. Ovaj kurs predstavlja preduslov za razumjevanje računarskih arhitektura.',5);
INSERT INTO "course" VALUES (5,'Razvoj programskih rješenja','U okviru kursa obrađuju se osnovni i napredni koncepti objektno orijentisanog programiranja. Obrađuje se i način kreiranje i dizajniranja korisničkog interfejsa, višenitnost, rad sa datotekama, rad sa grafičkim elementima.Sa stečenim znanjem studenti mogu dizajnirati i implementirati složenija programskih rješenja.',5);
INSERT INTO "course" VALUES (6,'Osnove računarskih mreža','Upoznavanje studenta sa osnovnim elementima LAN i WAN mreža: vrste mreža, stuktura mreža, metode pristupa, vrste uređaja, osnovni protokoli, rutiranje.
Po završetku kursa student bi trebao da zna osnovne elemente funkcionalnosti, dizajniranja, implementacije, administriranja i sigurnosti računarskih mreža.',5);
INSERT INTO "course" VALUES (7,'Digitalno procesiranje signala','Cilj predmeta je upoznavanje i razumjevanje osnovnih karakteristika vremenski diskretnih signala i sistema i njihov matematički prikaz. Potrebno je razviti sposobnost izvođenja i primjene algoritama za transformaciju signala i analize signala, razumjevanje njihovih karakteristika i implementacije. Slijedeći cilj je razviti sposobnost za dizajn i primjenu sistema u digitalnog procesiranja signala te razumjevanje njihovih karakteristika i implementacije, i na kraju upoznavanje sa primjerima hardverskog dizajna i primjenom digitalne obrade signala.',5);
INSERT INTO "course" VALUES (8,'CAD - CAM inženjering','Kurs će vam prenijeti bazna znanja i vještine iz metoda, tehnika i softverskih alata za računarski podržavano crtanje, dizajniranje, projektovanje i proizvodnju. Na kraju kursa student treba biti sposoban da iscrtava svoje ideje i provede grafički dizajn koristeći CAD sistem.',5);
INSERT INTO "course" VALUES (9,'Linearna algebra i geometrija','Cilj kursa je dati osnovna znanja iz linearne algebre i analitičke geometrije. Student treba biti u stanju analizirati rješivost sistema linearnih jednadžbi, koristeći matrice i operacije s matricama kao instrumente za formalizaciju i analizu podataka, te poznavati osnove teorije vektorskih prostora. U oblasti analitičke geometrije, nakon osvrta na dvodimenzionalni prostor, uvodi se analitička geometrija u trodimenzionalnom prostoru .',5);
INSERT INTO "course" VALUES (10,'Osnove elektrotehnike','Kurs ima za cilj studentima prezentirati osnovne koncepte iz elektromagnetizma i njihovo tretiranje pomoću matematičkih termina. Studenti trebaju postići znanja vezana za znanstvenu metodologiju i prirodne zakone na način da se s elektromagnetnim fenomenima i problemima koji su s njima u vezi susretnu kako s kvalitativnog, tako i s kvantitativnog aspekta.',5);
INSERT INTO "course" VALUES (11,'Uvod u programiranje','Student koji uspješno završi predmet će imati sljedeće kompetencije:

konceptualno razumijevanje strategija za rješavanje problema koristeći algoritamski pristup;
razumijevanje osnovne terminologije koja se koristi u programiranju;
dizajn jednostavnih programa u programskom jeziku C, što uključuje: naredbe za kontrolu toka programa, nizove, strukture, funkcije, pokazivače i ulazno-izlazne operacije,
pisanje, kompajliranje i debagiranje jednostavnih programa u programskom jeziku C.',5);
COMMIT;
