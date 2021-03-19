create schema if not exists database_journey_service;

create table database_journey_service.journey
(
	id serial,
	station_from varchar(50) not null,
	station_to varchar(50) not null,
	departure date not null ,
	arrival date not null ,
	route varchar(200) not null
);

INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-19', '2021-03-20', 'Odessa->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-20', '2021-03-21', 'Odessa->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-21', '2021-03-22', 'Odessa->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-19', '2021-03-20', 'Kiev->Odessa');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-20', '2021-03-21', 'Kiev->Odessa');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-21', '2021-03-22', 'Kiev->Odessa');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-19', '2021-03-20', 'Lviv->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-20', '2021-03-21', 'Lviv->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-21', '2021-03-22', 'Lviv->Kiev');
