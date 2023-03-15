
DROP TABLE Rata;
DROP TABLE Contract_j;
DROP TABLE Contract_m;
DROP TABLE Persoana;

CREATE TABLE Persoana (
  id_p int NOT NULL,
  nume VARCHAR(255) NOT NULL,
  email VARCHAR(255),
  adresa VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_p)
);

CREATE TABLE Contract_m (
  id_cm INT PRIMARY KEY,
  data DATE NOT NULL,
  functie VARCHAR(255) NOT NULL,
  salar_baza DECIMAL(10,2) NOT NULL,
  comision DECIMAL(10,2) NOT NULL,
  id_angajat INT NOT NULL,
  FOREIGN KEY (id_angajat) REFERENCES Persoana(id_p)
);

CREATE TABLE Contract_j (
  id_cj INT PRIMARY KEY,
  data DATE NOT NULL,
  obiect VARCHAR(255) NOT NULL,
  onorar DECIMAL(10,2) NOT NULL,
  nr_pagini INT NOT NULL,
  id_client INT NOT NULL,
  id_avocat INT NOT NULL,
  FOREIGN KEY (id_client) REFERENCES Persoana(id_p),
  FOREIGN KEY (id_avocat) REFERENCES Persoana(id_p)
);

CREATE TABLE Rata (
  id_cj INT NOT NULL,
  id_r INT PRIMARY KEY NOT NULL,
  data DATE NOT NULL,
  suma DECIMAL(10,2),
  FOREIGN KEY (id_cj) REFERENCES Contract_j(id_cj)
);

ALTER TABLE Persoana
ADD telefon VARCHAR(255);


INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (1, 'Ion Caprioara', 'ioncaprioara@mail.com', 'Strada Primavarii, numarul 23', '040180993');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (2, 'Traian Popescu', 'traianpopescu@mail.com', 'Strada Mihai viteazu, numarul 67', '072735955');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (3, 'Robert Dinu', 'robertdinu@mail.com', 'Strada Bucuresti, numarul 456', '075783720');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (4, 'Virgil Danu', 'virgildanu@mail.com', 'Strada Muzeului, numarul 420', '075185481');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (5, 'Florin Apostol', 'florinapostol@mail.com', 'Strada Patriei, numarul 90', '028924451');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (6, 'Georgiana Matescu', 'georgianamatescu@mail.com', 'Strada albinei, numarul 75', '054026104');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (7, 'Stelian Chirca', 'stelianchirca@mail.com', 'Strada Macesului, numarul 81', '037485630');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (8, 'Gabriela Marin', 'gabrielamarin@email.com', 'Strada Crizantemei, numarul 49', '098433519');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (9, 'Constantin Istrat', 'constantinistrat@mail.com', 'Strada Alunului, numarul 369', '039040379');
INSERT INTO Persoana (id_p, nume, email, adresa, telefon) VALUES (10, 'Ionut Iordache', 'ionutiordache@mail.com', 'Strada Izvorului, numarul 670', '080243349');


INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (1, '2022-11-04', 'actiune civila', 1000, 20, 1, 4);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (2, '2022-01-20', 'actiune penala', 200, 50, 3, 5);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (3, '2020-06-30', 'actiune civila', 1500, 25, 7, 6);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (4, '2022-09-24', 'actiune penala', 178, 30, 8, 4);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (5,  '2021-12-05', 'actiune civila', 1560, 34, 9, 5);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (6, '2022-01-15', 'actiune civila', 1780, 10, 1, 6);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (7, '2022-02-03', 'actiune penala', 330, 18, 3, 4);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (8, '2021-10-01' , 'actiune penala', 360, 26, 7, 5);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (9,  '2022-07-04', 'actiune civila', 900, 40, 8, 6);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (10, '2020-10-20', 'actiune penala', 100, 14, 9, 4);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (11, '2022-10-02' , 'actiune penala', 260, 26, 7, 3);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (12, '2022-10-10' , 'actiune civila', 1000, 26, 9, 5);
INSERT INTO Contract_j (id_cj, data, obiect, onorar, nr_pagini, id_client, id_avocat) VALUES (13, '2022-11-10' , 'actiune civila', 1000, 22, 3, 1);

INSERT INTO Contract_m (id_cm, data, functie, salar_baza, comision, id_angajat) VALUES (1, '2018-06-01', 'avocat', 4000, 0.5, 4);
INSERT INTO Contract_m (id_cm, data, functie, salar_baza, comision, id_angajat) VALUES (2, '2016-01-20', 'avocat', 5000, 0.8, 5);
INSERT INTO Contract_m (id_cm, data, functie, salar_baza, comision, id_angajat) VALUES (3, '2020-03-24', 'avocat', 4500, 0.6, 6);
INSERT INTO Contract_m (id_cm, data, functie, salar_baza, comision, id_angajat) VALUES (4, '2015-05-01', 'notar', 4000, 0.3, 2);
INSERT INTO Contract_m (id_cm, data, functie, salar_baza, comision, id_angajat) VALUES (5, '2022-08-11', 'notar', 3000, 0.1, 9);


INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (1, 1, '2022-12-01', 500);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (1, 11, '2022-12-06', 500);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (2, 2, '2022-12-01', 200);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (3, 3, '2022-12-01', 1000);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (4, 4, '2022-12-01', 178);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (5, 5, '2022-12-01', 1500);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (6, 6, '2022-12-01', 1500);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (7, 7, '2022-12-01', 330);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (8, 8, '2022-12-01', 400);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (9,9, '2022-12-01', 900);
INSERT INTO Rata (id_cj, id_r, data, suma) VALUES (10, 10, '2022-12-01',0);



SELECT * FROM CONTRACT_J WHERE YEAR(data) = 2022 
AND MONTH(data) = 10  AND ONORAR BETWEEN 900 AND 1500
ORDER BY DATA ASC;

SELECT * FROM CONTRACT_M WHERE FUNCTIE like 'a%'
ORDER BY SALAR_BAZA DESC, FUNCTIE ASC;

SELECT nume  
FROM Persoana
JOIN Contract_m 
  ON Persoana.id_p = Contract_m.id_angajat 
JOIN Contract_j 
  ON Persoana.id_p = Contract_j.id_avocat 
WHERE YEAR(Contract_m.data) = 2022
AND Contract_m.functie != 'avocat';

SELECT DISTINCT cj1.id_cj AS id_cj1, cj2.id_cj AS id_cj2
FROM Contract_j cj1
JOIN Contract_j cj2
  ON cj1.id_avocat = cj2.id_avocat
  AND cj1.id_cj < cj2.id_cj
  AND cj1.id_client <> cj2.id_client;

SELECT *
FROM Contract_m cm
WHERE EXISTS (
    SELECT *
    FROM Contract_m cm2
    WHERE cm2.comision = cm.comision
    AND cm2.id_cm != cm.id_cm
);

SELECT p.nume
FROM Persoana p
WHERE p.id_p IN (
    SELECT cj.id_avocat
    FROM Contract_j cj
    WHERE cj.onorar = (
        SELECT MAX(cj2.onorar)
        FROM Contract_j cj2
    )
);

SELECT p.nume, AVG(cj.onorar)
FROM Persoana p
JOIN Contract_j cj ON p.id_p = cj.id_avocat
WHERE cj.data >=  '2022-01-01' AND cj.data < '2023-01-01'
GROUP BY p.nume;

SELECT cj.id_cj
FROM Contract_j cj
WHERE NOT EXISTS (
    SELECT *
    FROM Rata r
    WHERE cj.id_cj = r.id_cj
    AND r.suma >= cj.onorar
);

