--liquibase formatted sql

--comment: Add dummy data to tables

--changeset Dasha:5

INSERT INTO theater
VALUES
    (2, 'Złote Tarasy, Złota 59, 00-120 Warszawa', 'Warszawa' , 'PL', 'multikino@mail.com', 52.21456, 21.0456, 'Multikino',
     '41 267 23 60', "https://multikino.pl/repertuar/warszawa-zlote-tarasy", 1),
    (3, 'Krakowskie Przedmieście 21/23, 00-071 Warszawa', 'Warszawa', 'PL', NULL, 48.21456, 26.0456, 'Kultura',
     '661 166 133', "http://www.kinokultura.pl/", 1),
    (4, 'Blue City, al. Jerozolimskie 179, 02-222 Warszawa', 'Warszawa', 'PL', NULL, 43.21456, 26.0456,  'Kino Helios',
     NULL, "https://www.helios.pl/57,Warszawa/StronaGlowna/", 1),
    (5, 'al. Jerozolimskie 148, 02-326 Warszawa', 'Warszawa', 'PL', NULL, 48.21456, 25.0456,
     'Multikino Reduta', NULL, "https://multikino.pl/repertuar/warszawa-atrium-reduta", 1),
    (6, 'Marszałkowska 28, 00-576 Warszawa', 'Warszawa', 'PL', NULL, 51.21856, 21.1756,
     'Kino Luna', NULL, "https://www.kinoluna.pl/", 1)
;

