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
	PRIMARY KEY("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "courseNews" (
	"id"	INTEGER,
	"news"	TEXT,
	"date"	TEXT,
	"courseId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("courseId") REFERENCES "course"("id")
);
CREATE TABLE IF NOT EXISTS "user" (
	"id"	INTEGER,
	"personId"	INTEGER,
	"email"	TEXT UNIQUE,
	"password"	TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("personId") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "courseMaterials" (
	"id"	INTEGER,
	"title"	TEXT,
	"path"	TEXT,
	"courseId"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("courseId") REFERENCES "course"("id")
);
INSERT INTO "course" VALUES (1,'Numerički algoritmi','Cilj ovog kursa je upoznavanje sa problemima vezanim za implementaciju računanja sa realnim brojevima na računaru, te dizajn brzih, tačnih i pouzdanih algoritama za rješavanje tipičnih problema numeričke prirode (računanja sa matricama, rješavanje jednačina i sistema jednačina, interpolacija, aproksimacija, numeričko diferenciranje i integriranje, numeričko rješavanje diferencijalnih jednačina, brza Fourierova transformacija).',5);
INSERT INTO "course" VALUES (2,'Algoritmi i strukture podataka','Cilj kursa je sticanja koherentnog znanja o tehnikama za implementiranje algoritama i strukturama podataka. U isto vrijeme kurs pruža studentima mogućnost da unaprijede svoje programersko znanje prilikom razvoja i primjene raznih algoritama u okviru konkretnih programskih rješenja.',5);
INSERT INTO "course" VALUES (3,'Diskretna matematika','Cilj kursa je da obezbijedi studentima solidne teorijske osnove kako bi na sistematičan način mogli rješavati matematske probleme informatičkog karaktera, a koji su vezani za elementarnu teoriju brojeva, kombinatoriku, elementarnu teoriju vjerovatnoće, teoriju informacija, teoriju grafova i teoriju diskretnih sistema.',5);
INSERT INTO "course" VALUES (4,'Logički dizajn
','Svrha ovog predmeta je uvođenje studenta u principe logičkog dizajna i projektovanja osnovnih komponenti digitalnog računara tradicionalnim i savremenim metodama. U hijerarhiji apstrakcija počinje od logičkih kola i završava sa jednostavnim ali funkcionalnim mikroprogramiranim procesorom (centralnom procesnom jedinicom) i njegovim mašinskim jezikom. Studenti treba da razumiju principe na kojima rade jednostavni računarski sistemi, kao i prednosti i nedostatke hardverskog i softverskog rješavanja problema. Preduslovi za razumjevanje gradiva ovog kursa su poznavanje osnova digitalne (prekidačke) elektronike kao i osnova računarstva. Ovaj kurs predstavlja preduslov za razumjevanje računarskih arhitektura.',5);
INSERT INTO "course" VALUES (5,'Razvoj programskih rješenja','U okviru kursa obrađuju se osnovni i napredni koncepti objektno orijentisanog programiranja. Obrađuje se i način kreiranje i dizajniranja korisničkog interfejsa, višenitnost, rad sa datotekama, rad sa grafičkim elementima.Sa stečenim znanjem studenti mogu dizajnirati i implementirati složenija programskih rješenja.',5);
INSERT INTO "course" VALUES (6,'Sistemsko programiranje','Cilj predmeta je da omoguć programeru uvid kako računarska mašina izvršava program, skladišti podatke i komunicira sa okolinom. Programeru se na praktičan načen, preko koncepta virtuelne ili pojednostavljene realne mašine prezentira programiranje na sistemskom nivou; od primitivnih mašinskih instrukcija do kompleksnih. Upoznaje se s odnosom viših programskih jezika i mašinskog jezika. Ovaj predmet je fundament za operativne sisteme, mreže, kompajlere i mnoge druge predmete koji se dotiču pitanja vezanih za sistemski nivo.',5);
INSERT INTO "course" VALUES (7,'Vjerovatnoća i statistika','Cilj kursa je studente poučiti o procedurama i modernim statističkim pristupima koji se koriste prilikom izučavanja komponenata i složenih sistema. Posebna pozornost posvećena je modernim tehnikama za izučavanje i predviđanja pouzdanosti sistema kao i tehnikama za poboljšanje pouzdanosti. Kurs istodobno daje studentima opća znanja iz vjerovatnosti i statistike budući da je u pitanju osnovni alat za savladavanje ovog kursa.',5);
INSERT INTO "titleInfo" VALUES (1,'R. prof. dr',3);
INSERT INTO "courseProfessor" VALUES (1,1,3);
INSERT INTO "courseStudent" VALUES (1,1,1);
INSERT INTO "courseStudent" VALUES (2,2,1);
INSERT INTO "courseStudent" VALUES (3,5,1);
INSERT INTO "person" VALUES (1,'Mehulić','Tarik','Rifet','Travnik','1234667123','062707906','tmehulic1@etf.unsa.ba',NULL,'1999-07-02','MALE',1,NULL);
INSERT INTO "person" VALUES (2,'Testić','Test','Testar','Sarajevo','4123123213','0620123044','test1@etf.unsa.ba',NULL,'2000-01-01','FEMALE',1,NULL);
INSERT INTO "person" VALUES (3,'Profesor','Profa','Profi','Zenica','12355464','065111323','prof@etf.unsa.ba',NULL,'1988-01-05','MALE',NULL,1);
INSERT INTO "educationInfo" VALUES (1,'Computer Science',1,3,18349,1);
INSERT INTO "educationInfo" VALUES (2,'Automatics',2,1,11111,2);
INSERT INTO "residenceInfo" VALUES (1,'Derviš ef. Korkuta 60/a','SBK','Travnik',1);
INSERT INTO "residenceInfo" VALUES (2,'Bjelave','KS','Sarajevo',2);
INSERT INTO "residenceInfo" VALUES (3,'Bosanska','ZDK','Zenica',3);
INSERT INTO "courseNews" VALUES (1,'Neka testna vijest, please work','2020-09-05',1);
INSERT INTO "user" VALUES (1,1,'tmehulic1@etf.unsa.ba','tarik123');
INSERT INTO "user" VALUES (2,2,'test1@etf.unsa.ba','test123');
INSERT INTO "user" VALUES (3,3,'prof@etf.unsa.ba','prof123');
COMMIT;
