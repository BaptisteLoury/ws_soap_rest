DROP DATABASE IF EXISTS trains;
CREATE DATABASE trains;
USE trains;

DROP TABLE IF EXISTS TRAINS;

CREATE TABLE `trains` (
  `TRAIN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TRAIN_NAME` varchar(50) NOT NULL,
  `TRAIN_SOURCE` varchar(50) NOT NULL,
  `TRAIN_DESTINATION` varchar(50) NOT NULL,
  `TRAIN_DEPARTURE_TIME` varchar(50) NOT NULL,
  `TRAIN_ARRIVAL_TIME` varchar(50) NOT NULL,
  `TRAIN_DATE` DATE NOT NULL,
  `TRAIN_PRICE` int(11) NOT NULL,
  `TRAIN_AVAILABILITY` int(11) NOT NULL,
  PRIMARY KEY (`TRAIN_ID`)
);


INSERT INTO TRAINS (TRAIN_NAME, TRAIN_SOURCE, TRAIN_DESTINATION, TRAIN_DEPARTURE_TIME, TRAIN_ARRIVAL_TIME, TRAIN_DATE, TRAIN_PRICE, TRAIN_AVAILABILITY) VALUES
('Thalys', 'Bordeaux', 'Marseille', '17:00', '10:00', '2022-06-09', 59, 168),
('TER', 'Lille', 'Marseille', '10:00', '17:00', '2022-07-18', 72, 173),
('Thalys', 'Lyon', 'Paris', '22:00', '17:00', '2022-10-15', 150, 69),
('Express', 'Lille', 'Marseille', '16:00', '19:00', '2022-01-22', 115, 183),
('Thalys', 'Bordeaux', 'Paris', '16:00', '20:00', '2022-06-24', 127, 39),
('TER', 'Marseille', 'Lille', '20:00', '21:00', '2022-10-17', 105, 90),
('TER', 'Lille', 'Paris', '11:00', '19:00', '2022-01-03', 147, 57),
('TGV', 'Lille', 'Lyon', '09:00', '16:00', '2022-05-17', 138, 50),
('TER', 'Bordeaux', 'Lille', '21:00', '12:00', '2022-11-11', 64, 100),
('Express', 'Paris', 'Bordeaux', '18:00', '16:00', '2022-10-13', 105, 200),
('Thalys', 'Paris', 'Lille', '20:00', '17:00', '2022-03-12', 145, 105),
('Thalys', 'Lyon', 'Lille', '15:00', '15:00', '2022-03-27', 84, 178),
('TGV', 'Bordeaux', 'Lille', '21:00', '09:00', '2022-01-24', 50, 192),
('Thalys', 'Lille', 'Bordeaux', '10:00', '09:00', '2022-12-23', 135, 144),
('Express', 'Lille', 'Paris', '22:00', '22:00', '2022-07-01', 92, 38),
('Express', 'Marseille', 'Lille', '21:00', '19:00', '2022-08-20', 116, 78),
('TGV', 'Paris', 'Bordeaux', '13:00', '08:00', '2022-05-17', 74, 141),
('Thalys', 'Lyon', 'Bordeaux', '08:00', '12:00', '2022-03-12', 107, 109),
('Thalys', 'Paris', 'Lyon', '13:00', '17:00', '2022-10-18', 82, 58),
('TER', 'Paris', 'Bordeaux', '10:00', '14:00', '2022-07-05', 65, 165),
('Express', 'Bordeaux', 'Lyon', '10:00', '22:00', '2022-03-17', 127, 22),
('Thalys', 'Marseille', 'Paris', '08:00', '10:00', '2022-08-11', 111, 35),
('Express', 'Marseille', 'Lille', '14:00', '13:00', '2022-03-25', 92, 88),
('TER', 'Lille', 'Marseille', '20:00', '19:00', '2022-09-08', 104, 61),
('Express', 'Lyon', 'Paris', '14:00', '12:00', '2022-11-09', 76, 125);
