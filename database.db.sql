BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "course" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL UNIQUE,
	"description"	TEXT,
	"ects"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "educationInfo" (
	"id"	INTEGER,
	"degree"	TEXT,
	"year"	INTEGER,
	"index"	INTEGER,
	"personId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "titleInfo" (
	"id"	INTEGER,
	"title"	TEXT,
	"personId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "courseNews" (
	"id"	INTEGER,
	"news"	TEXT,
	"courseId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("courseId") REFERENCES "course"("id")
);
CREATE TABLE IF NOT EXISTS "courseProfessor" (
	"id"	INTEGER,
	"courseId"	INTEGER,
	"personId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id"),
	FOREIGN KEY("courseId") REFERENCES "course"("id")
);
CREATE TABLE IF NOT EXISTS "courseStudent" (
	"id"	INTEGER,
	"courseId"	INTEGER,
	"personId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id"),
	FOREIGN KEY("courseId") REFERENCES "course"("id")
);
CREATE TABLE IF NOT EXISTS "person" (
	"id"	INTEGER,
	"lastName"	TEXT,
	"firstName"	TEXT,
	"birthPlace"	TEXT,
	"jmbg"	TEXT,
	"phone"	TEXT,
	"email"	TEXT NOT NULL UNIQUE,
	"image"	TEXT,
	"birthDate"	TEXT,
	"gender"	TEXT,
	"student"	INTEGER,
	"professor"	INTEGER,
	PRIMARY KEY("id")
);
INSERT INTO "course" VALUES (1,'Numerički algoritmi','Cilj ovog kursa je upoznavanje sa problemima vezanim za implementaciju računanja sa realnim brojevima na računaru, te dizajn brzih, tačnih i pouzdanih algoritama za rješavanje tipičnih problema numeričke prirode (računanja sa matricama, rješavanje jednačina i sistema jednačina, interpolacija, aproksimacija, numeričko diferenciranje i integriranje, numeričko rješavanje diferencijalnih jednačina, brza Fourierova transformacija).',5);
INSERT INTO "course" VALUES (2,'Algoritmi i strukture podataka','Cilj kursa je sticanja koherentnog znanja o tehnikama za implementiranje algoritama i strukturama podataka. U isto vrijeme kurs pruža studentima mogućnost da unaprijede svoje programersko znanje prilikom razvoja i primjene raznih algoritama u okviru konkretnih programskih rješenja.',5);
INSERT INTO "course" VALUES (3,'Diskretna matematika','Cilj kursa je da obezbijedi studentima solidne teorijske osnove kako bi na sistematičan način mogli rješavati matematske probleme informatičkog karaktera, a koji su vezani za elementarnu teoriju brojeva, kombinatoriku, elementarnu teoriju vjerovatnoće, teoriju informacija, teoriju grafova i teoriju diskretnih sistema.',5);
INSERT INTO "course" VALUES (4,'Logički dizajn
','Svrha ovog predmeta je uvođenje studenta u principe logičkog dizajna i projektovanja osnovnih komponenti digitalnog računara tradicionalnim i savremenim metodama. U hijerarhiji apstrakcija počinje od logičkih kola i završava sa jednostavnim ali funkcionalnim mikroprogramiranim procesorom (centralnom procesnom jedinicom) i njegovim mašinskim jezikom. Studenti treba da razumiju principe na kojima rade jednostavni računarski sistemi, kao i prednosti i nedostatke hardverskog i softverskog rješavanja problema. Preduslovi za razumjevanje gradiva ovog kursa su poznavanje osnova digitalne (prekidačke) elektronike kao i osnova računarstva. Ovaj kurs predstavlja preduslov za razumjevanje računarskih arhitektura.',5);
INSERT INTO "course" VALUES (5,'Razvoj programskih rješenja','U okviru kursa obrađuju se osnovni i napredni koncepti objektno orijentisanog programiranja. Obrađuje se i način kreiranje i dizajniranja korisničkog interfejsa, višenitnost, rad sa datotekama, rad sa grafičkim elementima.Sa stečenim znanjem studenti mogu dizajnirati i implementirati složenija programskih rješenja.',5);
INSERT INTO "course" VALUES (6,'Sistemsko programiranje','Cilj predmeta je da omoguć programeru uvid kako računarska mašina izvršava program, skladišti podatke i komunicira sa okolinom. Programeru se na praktičan načen, preko koncepta virtuelne ili pojednostavljene realne mašine prezentira programiranje na sistemskom nivou; od primitivnih mašinskih instrukcija do kompleksnih. Upoznaje se s odnosom viših programskih jezika i mašinskog jezika. Ovaj predmet je fundament za operativne sisteme, mreže, kompajlere i mnoge druge predmete koji se dotiču pitanja vezanih za sistemski nivo.',5);
INSERT INTO "course" VALUES (7,'Vjerovatnoća i statistika','Cilj kursa je studente poučiti o procedurama i modernim statističkim pristupima koji se koriste prilikom izučavanja komponenata i složenih sistema. Posebna pozornost posvećena je modernim tehnikama za izučavanje i predviđanja pouzdanosti sistema kao i tehnikama za poboljšanje pouzdanosti. Kurs istodobno daje studentima opća znanja iz vjerovatnosti i statistike budući da je u pitanju osnovni alat za savladavanje ovog kursa.',5);
COMMIT;
