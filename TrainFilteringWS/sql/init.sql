USE rest;

DROP TABLE IF EXISTS TRAINS;

CREATE TABLE `TRAINS` (
  `TRAIN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TRAIN_NAME` varchar(50) NOT NULL,
  `TRAIN_SOURCE` varchar(50) NOT NULL,
  `TRAIN_DESTINATION` varchar(50) NOT NULL,
  `TRAIN_DEPARTURE_TIME` varchar(50) NOT NULL,
  `TRAIN_ARRIVAL_TIME` varchar(50) NOT NULL,
  `TRAIN_DEPARTURE_DATE` DATE NOT NULL,
  `TRAIN_ARRIVAL_DATE` DATE NOT NULL,
  `TRAIN_PRICE` int(11) NOT NULL,
  `TRAIN_AVAILABILITY` int(11) NOT NULL,
  PRIMARY KEY (`TRAIN_ID`)
);

CREATE TABLE RESERVATIONS (
  `RESERVATION_ID` INT PRIMARY KEY AUTO_INCREMENT,
  `RESERVATION_FIRSTNAME` VARCHAR(255) NOT NULL,
  `RESERVATION_LASTNAME` VARCHAR(255) NOT NULL,
  `TRAIN_ID` INT NOT NULL,
  FOREIGN KEY (TRAIN_ID) REFERENCES TRAINS (TRAIN_ID) ON DELETE CASCADE
);

INSERT INTO TRAINS (TRAIN_NAME, TRAIN_SOURCE, TRAIN_DESTINATION, TRAIN_DEPARTURE_TIME, TRAIN_ARRIVAL_TIME, TRAIN_DEPARTURE_DATE, TRAIN_ARRIVAL_DATE, TRAIN_PRICE, TRAIN_AVAILABILITY) VALUES
('TGV', 'Bordeaux', 'Paris', '13:00', '16:00', '2022-11-24', '2022-11-24', 64, 47),
('TGV', 'Lyon', 'Bordeaux', '17:00', '09:00', '2022-12-28', '2022-12-28', 76, 36),
('Express', 'Lyon', 'Lille', '19:00', '18:00', '2022-08-09', '2022-08-09', 146, 109),
('TER', 'Lyon', 'Paris', '22:00', '21:00', '2022-08-01', '2022-08-01', 98, 39),
('Express', 'Lille', 'Lyon', '12:00', '13:00', '2022-08-24', '2022-08-24', 71, 128),
('TER', 'Lyon', 'Bordeaux', '16:00', '10:00', '2022-02-22', '2022-02-22', 68, 109),
('Express', 'Marseille', 'Lille', '09:00', '16:00', '2022-07-13', '2022-07-13', 130, 53),
('TGV', 'Marseille', 'Bordeaux', '21:00', '12:00', '2022-06-17', '2022-06-17', 57, 31),
('TER', 'Lyon', 'Paris', '08:00', '17:00', '2022-12-27', '2022-12-27', 106, 91),
('Express', 'Paris', 'Marseille', '22:00', '14:00', '2022-12-02', '2022-12-02', 66, 128),
('Thalys', 'Bordeaux', 'Lille', '19:00', '09:00', '2022-03-07', '2022-03-07', 71, 157),
('TGV', 'Paris', 'Lille', '15:00', '11:00', '2022-03-19', '2022-03-19', 69, 199),
('Thalys', 'Lille', 'Marseille', '14:00', '10:00', '2022-02-28', '2022-02-28', 94, 153),
('TER', 'Marseille', 'Bordeaux', '14:00', '09:00', '2022-05-06', '2022-05-06', 66, 96),
('TER', 'Bordeaux', 'Lyon', '11:00', '11:00', '2022-05-18', '2022-05-18', 132, 160),
('Express', 'Lille', 'Marseille', '22:00', '18:00', '2022-05-20', '2022-05-20', 105, 39),
('Thalys', 'Lille', 'Paris', '11:00', '19:00', '2022-01-07', '2022-01-07', 134, 45),
('TGV', 'Paris', 'Bordeaux', '21:00', '12:00', '2022-02-05', '2022-02-05', 135, 21),
('Express', 'Marseille', 'Bordeaux', '21:00', '15:00', '2022-02-24', '2022-02-24', 56, 108),
('Thalys', 'Marseille', 'Paris', '10:00', '19:00', '2022-05-21', '2022-05-21', 82, 32),
('TER', 'Bordeaux', 'Lille', '12:00', '14:00', '2022-10-24', '2022-10-24', 97, 165),
('Express', 'Lille', 'Marseille', '20:00', '09:00', '2022-11-06', '2022-11-06', 117, 111),
('TGV', 'Paris', 'Lyon', '14:00', '20:00', '2022-10-11', '2022-10-11', 77, 155),
('Express', 'Lille', 'Bordeaux', '13:00', '08:00', '2022-12-10', '2022-12-10', 91, 188),
('TGV', 'Paris', 'Marseille', '08:00', '20:00', '2022-09-09', '2022-09-09', 52, 61);
