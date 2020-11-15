# MBRS_2020
Projektni zadatak iz Metodologija brzog razvoja softvera 2020

# Uputstvo za generisanje plug-ina i koda
- Potrebno je instalirati MagicDraw 17.0.3
- Sa canvas board-a iz Files/MagicDraw preuzeti txt file koji sadrzi licence key i pomocu toga aktivirati punu verziju MagicDraw-a
- Podesiti Run Configuration za pokretanje MagicDraw-a iz eclipse-a po uputstvu sa vezbi broj 3
- Postaviti MAGICDRAW_HOME=/MagicDraw u build.properties fajlu
- Desni klik na build.xml u eclipse-u i odraditi Ant deploy
- Ukoliko je Build Succeeded, proveriti u C:/MagicDraw da li je generisan folder plugins sa generisanim pluginom unutra
- Ako jeste, kopirati folder myplugin u C:\Program Files\MagicDraw UML\plugins
- Zatim u eclipse-u pokrenuti kreiranu Run konfiguraciju
- Kada se MagicDraw pokrene, otvoriti model
- U menu bar-u bi sada trebalo da postoji CodeGeneration opcija, i tu kliknuti na Generate
