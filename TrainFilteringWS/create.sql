DROP DATABASE IF EXISTS trains;
CREATE DATABASE trains;
USE trains;

DROP TABLE IF EXISTS TRAINS;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `trains` (
  `TRAIN_ID` int(11) NOT NULL,
  `TRAIN_NAME` varchar(50) NOT NULL,
  `TRAIN_TYPE` varchar(50) NOT NULL,
  `TRAIN_SOURCE` varchar(50) NOT NULL,
  `TRAIN_DESTINATION` varchar(50) NOT NULL,
  `TRAIN_DEPARTURE_TIME` varchar(50) NOT NULL,
  `TRAIN_ARRIVAL_TIME` varchar(50) NOT NULL,
  `TRAIN_PRICE` int(11) NOT NULL,
  `TRAIN_AVAILABILITY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `trains` (`TRAIN_ID`, `TRAIN_NAME`, `TRAIN_TYPE`, `TRAIN_SOURCE`, `TRAIN_DESTINATION`, `TRAIN_DEPARTURE_TIME`, `TRAIN_ARRIVAL_TIME`, `TRAIN_PRICE`, `TRAIN_AVAILABILITY`) VALUES
(1, 'TGV', 'A', 'Lyon', 'Paris', '22:00', '13:00', 133, 24),
(2, 'TGV', 'A', 'Marseille', 'Lyon', '21:00', '18:00', 94, 101),
(3, 'Express', 'C', 'Paris', 'Lyon', '20:00', '08:00', 130, 154),
(4, 'Thalys', 'C', 'Paris', 'Lille', '22:00', '14:00', 50, 90),
(5, 'Express', 'D', 'Marseille', 'Lyon', '10:00', '13:00', 51, 133),
(6, 'Express', 'D', 'Bordeaux', 'Lyon', '12:00', '20:00', 61, 93),
(7, 'Thalys', 'D', 'Paris', 'Marseille', '20:00', '09:00', 132, 174),
(8, 'Thalys', 'B', 'Lille', 'Marseille', '15:00', '13:00', 103, 167),
(9, 'Express', 'C', 'Marseille', 'Lille', '14:00', '20:00', 59, 144),
(10, 'TGV', 'B', 'Lille', 'Marseille', '16:00', '12:00', 104, 136),
(11, 'TGV', 'B', 'Lyon', 'Bordeaux', '20:00', '19:00', 106, 74),
(12, 'Express', 'B', 'Lille', 'Bordeaux', '20:00', '17:00', 118, 128),
(13, 'Express', 'C', 'Bordeaux', 'Paris', '10:00', '12:00', 141, 28),
(14, 'TGV', 'D', 'Bordeaux', 'Paris', '16:00', '20:00', 67, 160),
(15, 'Express', 'B', 'Lyon', 'Marseille', '19:00', '16:00', 71, 39),
(16, 'TER', 'A', 'Lyon', 'Lille', '16:00', '20:00', 105, 80),
(17, 'TGV', 'A', 'Bordeaux', 'Paris', '10:00', '21:00', 109, 183),
(18, 'Express', 'C', 'Marseille', 'Paris', '22:00', '15:00', 84, 145),
(19, 'TGV', 'C', 'Lyon', 'Paris', '15:00', '18:00', 61, 135),
(20, 'TER', 'A', 'Marseille', 'Lyon', '08:00', '17:00', 70, 188),
(21, 'Thalys', 'B', 'Paris', 'Lille', '09:00', '22:00', 141, 146),
(22, 'TER', 'C', 'Marseille', 'Lille', '08:00', '12:00', 120, 159),
(23, 'TER', 'B', 'Bordeaux', 'Paris', '14:00', '16:00', 69, 150),
(24, 'TGV', 'D', 'Marseille', 'Bordeaux', '18:00', '14:00', 78, 142),
(25, 'Thalys', 'C', 'Paris', 'Lille', '10:00', '11:00', 141, 192);


ALTER TABLE `trains`
  ADD PRIMARY KEY (`TRAIN_ID`);


ALTER TABLE `trains`
  MODIFY `TRAIN_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;
