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

INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-21', '2021-03-22', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-22', '2021-03-23', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-23', '2021-03-24', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-21', '2021-03-22', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-22', '2021-03-23', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-23', '2021-03-24', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-21', '2021-03-22', 'Lviv->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-22', '2021-03-23', 'Lviv->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-23', '2021-03-24', 'Lviv->Kiev');
