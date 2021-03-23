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

INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-24', '2021-03-25', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-25', '2021-03-26', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-26', '2021-03-27', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-24', '2021-03-25', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-25', '2021-03-26', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-26', '2021-03-27', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-24', '2021-03-25', 'Lviv->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-25', '2021-03-26', 'Lviv->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-26', '2021-03-27', 'Lviv->Kiev');
