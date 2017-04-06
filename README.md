# Informatik B Testatlösungen

[![Build Status](https://travis-ci.org/deichbrise/binf.svg?branch=master)](https://travis-ci.org/deichbrise/binf)

## Bauen der Resourcen

Per Konsole

    ./build.sh

Hier werden die Klassen nach bin kompiliert, als JAR gepackt und eine JavaDoc in javadoc generiert.

## Tests ausführen

    java -jar bin/sources.jar

Oder spezifische Tests:

    java -jar bin/sources.jar "[regex]"
    
Ein Beispielskript liegt unter week1.sh bereit.
