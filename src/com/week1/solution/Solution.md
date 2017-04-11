# Aufgabe 1.1

| Stelle | a  | b  | c             |
|--------|----|----|---------------|
| 1      | 5  | /  | {16, 4, 2, 9} |
| 2      | 23 | 0  | /             |
| 3      | 5  | /  | {16, 4, 2, 9} |
| 4      | 5  | -1 | {16, 4, 2, 9} |
| 5      | 2  | /  | {16, 0, 2, 9} |
| 6      | 2  | 2  | {16, 0, 0, 9} |
| 7      | 3  | /  | {16, 0, 0, 9} |
| 8      | 23 | 19 | /             |
| 9      | 3  | 23 | {16, 0, 0, 9} |
| 10     | 5  | /  | {0, 0, 0, 0}  |
| 11     | 4  | 12 | {8, 0, 0, 9}  |
| 12     | 23 | 4  | /             |
| 13     | 24 | /  | {0, 0, 0, 0}  |
| 14     | 23 | 24 | /             |
| 15     | 23 | 12 | {8, 0, 0, 9}  |

### Call-by-Value

Call-by-value bedeutet, dass beim Übergeben eines Parameters an eine Methode keine Referenz auf die Speicheradresse übergeben wird,
sondern eine Kopie angelegt wird. Bei primitiven Datentypen hat dies den Effekt, dass Änderungen
der Variable sich nur innerhalb der Methode auswirken und somit wenige bis keine Nebeneffekte entstehen. Bei Objekten sowie
Arrays steht allerdings nicht der eigentliche Wert in der Speicheradresse, sondern nur eine Referenz bzw. eine Zeiger auf eine andere Speicheradresse,
sodass hier nur der Zeiger, aber nicht das eigentliche Objekt kopiert wird und somit Änderungen auch außerhalb der Methode
sichbar werden können. Dieses Verhalten kennen wir aus Java.

### Call-by-reference
Anders als bei call-by-value wird hier die eigentliche Speicheradresse als Parameter an die Methode übergeben, sodass auch
Änderungen nach außen sichtbar werden können.

