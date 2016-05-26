/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  fonzi
 * Created: 19.05.2016
 */
-- DMLs DummyDatensätze wiederherstellen

-- Datenbank vcetrainer
use vcetrainer;

-- alle DS in allen Tabellen bis auf themenbereich löschen,
-- AUTO_INCREMENT auf 1 setzen
TRUNCATE lernkarte2themenbereich;
TRUNCATE potentielleantwort;
DELETE FROM lernkarte;
ALTER TABLE lernkarte AUTO_INCREMENT=1;

-- 2 DS für komplette Lernkarten erstellen
-- LK 1
INSERT INTO lernkarte VALUES (NULL, "Ja, wo bin ich?", NULL);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 1, 1);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 1, 2);
INSERT INTO potentielleantwort VALUES(NULL, "true", "qwertzu", 1);
INSERT INTO potentielleantwort VALUES(NULL, "false", "Raum 5.2", 1);

-- LK 2
INSERT INTO lernkarte VALUES (NULL, "Frage an Alle:wie spät ist es?", NULL);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 2, 3);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 2, 4);
INSERT INTO potentielleantwort VALUES(NULL, "true", "12 Uhr 30", 2);
INSERT INTO potentielleantwort VALUES(NULL, "false", "6:00 Uhr", 2);

-- LK 3
INSERT INTO lernkarte VALUES (NULL, "Neue Lernkarte?", NULL);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 3, 5);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 3, 6);
INSERT INTO potentielleantwort VALUES(NULL, "true", "Mittag um 12,30", 3);
INSERT INTO potentielleantwort VALUES(NULL, "false", "Frühstück um 9.30", 3);



-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 13. Mai 2016 um 12:31
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `vcetrainer`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lernkarte2themenbereich`
--

CREATE TABLE `lernkarte2themenbereich` (
  `id` int(11) NOT NULL,
  `lernkarte_id` int(11) NOT NULL,
  `themenbereich_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lernkarte`
--

CREATE TABLE `lernkarte` (
  `id` int(11) NOT NULL,
  `frage` varchar(2048) DEFAULT NULL,
  `schwierigkeitsgrad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `potentielleantwort`
--

CREATE TABLE `potentielleantwort` (
  `id` int(11) NOT NULL,
  `richtigkeit` enum('true','false') DEFAULT NULL,
  `antwort` varchar(1024) DEFAULT NULL,
  `lernkarte_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `themenbereich`
--

CREATE TABLE `themenbereich` (
  `id` int(11) NOT NULL,
  `bezeichnung` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `lernkarte2themenbereich`
--
ALTER TABLE `lernkarte2themenbereich`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lernkarte_id` (`lernkarte_id`),
  ADD KEY `themenbereich_id` (`themenbereich_id`);

--
-- Indizes für die Tabelle `lernkarte`
--
ALTER TABLE `lernkarte`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `potentielleantwort`
--
ALTER TABLE `potentielleantwort`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lernkarte_id` (`lernkarte_id`);

--
-- Indizes für die Tabelle `themenbereich`
--
ALTER TABLE `themenbereich`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `lernkarte2themenbereich`
--
ALTER TABLE `lernkarte2themenbereich`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `lernkarte`
--
ALTER TABLE `lernkarte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `potentielleantwort`
--
ALTER TABLE `potentielleantwort`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `themenbereich`
--
ALTER TABLE `themenbereich`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `lernkarte2themenbereich`
--
ALTER TABLE `lernkarte2themenbereich`
  ADD CONSTRAINT `lernkarte2themenbereich_ibfk_1` FOREIGN KEY (`lernkarte_id`) REFERENCES `lernkarte` (`id`),
  ADD CONSTRAINT `lernkarte2themenbereich_ibfk_2` FOREIGN KEY (`themenbereich_id`) REFERENCES `themenbereich` (`id`);

--
-- Constraints der Tabelle `potentielleantwort`
--
ALTER TABLE `potentielleantwort`
  ADD CONSTRAINT `potentielleantwort_ibfk_1` FOREIGN KEY (`lernkarte_id`) REFERENCES `lernkarte` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
