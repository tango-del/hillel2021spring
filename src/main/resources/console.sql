-- drop table if exists journey;

create table journey
(
    id serial,
    station_from varchar(50) not null,
    station_to varchar(50) not null,
    departure date not null ,
    arrival date not null ,
    route varchar(200) not null
);

INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-27', '2021-03-28', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-28', '2021-03-29', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-29', '2021-03-30', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-27', '2021-03-28', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-28', '2021-03-29', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-29', '2021-03-30', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-27', '2021-03-28', 'Lviv->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-28', '2021-03-29', 'Lviv->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-29', '2021-03-30', 'Lviv->Kiev');
