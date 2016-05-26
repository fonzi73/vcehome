/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  fonzi
 * Created: 26.05.2016
 */

--
-- Tabellenstruktur für Tabelle `benutzer`
--

CREATE TABLE `benutzer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL UNIQUE,
  `passwort` varchar(45) NOT NULL,
  `vorname` varchar(45) NOT NULL,
  `nachname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lernsitzung`
--

CREATE TABLE `lernsitzung` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typ` enum('Test','Lern','LernR','ungewertet') DEFAULT NULL,
  `datum` DATE NOT NULL,
  `benutzer_id` int(11) NOT NULL,  
  PRIMARY KEY (`id`),
  FOREIGN KEY (`benutzer_id`) REFERENCES `benutzer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lernsitzung2potentielleantwort`
--

CREATE TABLE `lernsitzung2potentielleantwort` (
  `lernsitzung_id` int(11) NOT NULL,
  `potentielleantwort_id` int(11) NOT NULL,
  PRIMARY KEY (`lernsitzung_id`,`potentielleantwort_id`),
  FOREIGN KEY (`lernsitzung_id`) REFERENCES `lernsitzung` (`id`),
  FOREIGN KEY (`potentielleantwort_id`) REFERENCES `potentielleantwort` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `benutzer2lernkarte`
--

CREATE TABLE `benutzer2lernkarte` (
  `benutzer_id` int(11) NOT NULL,
  `lernkarte_id` int(11) NOT NULL,
  `wiedervorlage` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`benutzer_id`,`lernkarte_id`),
  FOREIGN KEY (`benutzer_id`) REFERENCES `benutzer` (`id`),
  FOREIGN KEY (`lernkarte_id`) REFERENCES `lernkarte` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lernsitzung2lernkarte`
--

CREATE TABLE `lernsitzung2lernkarte` (
  `lernsitzung_id` int(11) NOT NULL,
  `lernkarte_id` int(11) NOT NULL,
  `gemogelt` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`lernsitzung_id`,`lernkarte_id`),
  FOREIGN KEY (`lernsitzung_id`) REFERENCES `lernsitzung` (`id`),
  FOREIGN KEY (`lernkarte_id`) REFERENCES `lernkarte` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------