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

INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-13', '2021-03-14', 'Odessa->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-14', '2021-03-15', 'Odessa->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-15', '2021-03-16', 'Odessa->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-13', '2021-03-14', 'Kiev->Odessa');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-14', '2021-03-15', 'Kiev->Odessa');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-15', '2021-03-16', 'Kiev->Odessa');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-13', '2021-03-14', 'Lviv->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-14', '2021-03-15', 'Lviv->Kiev');
INSERT INTO database_journey_service.journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-15', '2021-03-16', 'Lviv->Kiev');
