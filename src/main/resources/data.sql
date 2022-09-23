INSERT INTO Siteuser (first_name,last_name,email,password) VALUES
 ('Vincent','Andersson','vincent@kvarteret.se','123'),
 ('Ulf','Heden','ulf@kvarteret.se','456'),
 ('Rebecca','Rivera','rebecca@kvarteret.se','789'),
 ('Yasaman','Mojaverian','yasaman@kvarteret.se','abc');

INSERT INTO Item (name,description,price,siteuser_id) VALUES
 ('AMSTRAD CPC464','Slö men funkar',299,1),
 ('Commodore 128','Lite rostig',500,1),
 ('Häst','Islänning',2500,3),
 ('Lampa','Blablabla',290,4),
 ('Dammsugare','Blablabla',35,2),
 ('Kratta','Blablabla',120,2),
 ('Diskmaskin','Blablabla',599,3),
 ('Blandade verktyg','Blablabla',15,4),
 ('Diskborste','Blablabla',75,3),
 ('Dator','Blablabla',90,2),
 ('Fräs','Blablabla',1999,3);
