create table WeatherInfo(
    id int auto_increment,
    name varchar(255) NOT NULL ,
    wmo int NOT NULL,
    temperature real NOT NULL,
    wind real NOT NULL,
    time int NOT NULL
);

insert into WeatherInfo(name, wmo, temperature, wind, time) values('Tallinn', 26038, 1.1, 1, 1710873722);
