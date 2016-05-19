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
