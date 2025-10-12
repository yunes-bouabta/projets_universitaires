CREATE TABLE table_plate (
  country VARCHAR,
  iso2 VARCHAR,
  iso3 VARCHAR,
  region_code INTEGER,
  region VARCHAR,
  sub_region_code INTEGER,
  sub_region VARCHAR,
  disaster VARCHAR,
  year INTEGER,
  number INTEGER
);

/*
Commande SQL à taper pour remplir le fichier plat à l'image du fichier plat
Note: Le chemin doit être adapté lorsqu'il est téléchargé.

\copy table_plate FROM C:\{chemin_vers_le_fichier}\Climate_related_disasters_frequency.csv

*/

CREATE TABLE region (
  region_code INTEGER PRIMARY KEY,
  name VARCHAR
);

CREATE TABLE sub_region (
  sub_region_code INTEGER PRIMARY KEY,
  name VARCHAR,
  region_code INTEGER REFERENCES region (region_code) ON DELETE CASCADE
);

CREATE TABLE country (
  country_code SERIAL PRIMARY KEY,
  name VARCHAR,
  iso2 VARCHAR,
  iso3 VARCHAR,
  sub_region_code INTEGER REFERENCES sub_region (sub_region_code) ON DELETE CASCADE
);

CREATE TABLE disaster (
  disaster_code SERIAL PRIMARY KEY,
  disaster VARCHAR
);

CREATE TABLE climate_disaster (
  country_code INTEGER REFERENCES country (country_code) ON DELETE CASCADE,
  disaster_code INTEGER REFERENCES disaster (disaster_code) ON DELETE CASCADE,
  year INTEGER,
  number INTEGER,
  PRIMARY KEY (country_code, disaster_code, year)
);

/*
ALIMENTATION DE LA BASE DE DONNEES DEPUIS LE FICHIER PLAT EXCEL 
OU LES DONNEES SONT DANS LA TABLE "table_plate"
*/

INSERT INTO region (region_code, name)
SELECT DISTINCT region_code, region
FROM table_plate;

INSERT INTO sub_region (sub_region_code, name, region_code)
SELECT DISTINCT sub_region_code, sub_region, region_code
FROM table_plate;

INSERT INTO country (name, iso2, iso3, sub_region_code)
SELECT DISTINCT country, iso2, iso3, sub_region_code
FROM table_plate;

INSERT INTO disaster (disaster)
SELECT DISTINCT disaster
FROM table_plate;

INSERT INTO climate_disaster (country_code, disaster_code, year, number)
SELECT DISTINCT c.country_code, d.disaster_code, tp.year, tp.number
FROM table_plate tp
JOIN country c ON tp.iso3 = c.iso3
JOIN disaster d ON tp.disaster = d.disaster;