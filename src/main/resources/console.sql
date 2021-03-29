drop table if exists journey;

create table journey
(
    id           bigint generated by default as identity
        constraint journey_pkey
            primary key,
    arrival      date         not null,
    departure    date         not null,
    route        varchar(255) not null,
    station_from varchar(255) not null,
    station_to   varchar(255) not null
);

alter table journey owner to postgres;

INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-29', '2021-03-30', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-30', '2021-03-31', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Odessa', 'Kiev', '2021-03-31', '2021-04-01', 'Odessa->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-29', '2021-03-30', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-30', '2021-03-31', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Kiev', 'Odessa', '2021-03-31', '2021-04-01', 'Kiev->Odessa');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-29', '2021-03-30', 'Lviv->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-30', '2021-03-31', 'Lviv->Kiev');
INSERT INTO journey (station_from, station_to, departure, arrival, route) VALUES ('Lviv', 'Kiev', '2021-03-31', '2021-04-01', 'Lviv->Kiev');
