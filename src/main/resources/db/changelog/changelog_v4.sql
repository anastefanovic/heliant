ALTER TABLE formular
ADD COLUMN id_korisnik_kreirao INT,
ADD COLUMN id_korisnik_poslednji_azurirao INT;

ALTER TABLE polje
ADD COLUMN id_korisnik_kreirao INT,
ADD COLUMN id_korisnik_poslednji_azurirao INT;

ALTER TABLE formular_popunjen
ADD COLUMN id_korisnik_kreirao INT,
ADD COLUMN id_korisnik_poslednji_azurirao INT;

ALTER TABLE polje_popunjeno
ADD COLUMN id_korisnik_kreirao INT,
ADD COLUMN id_korisnik_poslednji_azurirao INT;