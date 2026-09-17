# Rytäkkä

## Peli, jossa rytisee

## Uusin versio (17.9.2026): 0.1

### **Todo:**
 - Vihollisten speksit: määrittele kaikkien vihollisten statsit, kyvyt jne.
 - Tiimien hallinta: Pelaaja voi valita tiimin värin/joukkueen. Hahmot voidaan värikoodata esim. shaderin avulla.
 - Ostovalikkoon hahmojen nimet, hinnat ja indikaattori siihen, onko varaa (muuta harmaaksi jos ei).
 - Vihollisille tekoäly, joka yrittää hyökätä pelaajaa vastaan.
 - Mekaniikat rahan keräämiseen, esim. vihollisten tappaminen, rahaa ajan kanssa, kultakaivosrakennelma?
 - Slotteja kentälle, joihin pelaaja ja viholliset voivat rakentaa rakennelmiaan.

### Versio 0.1
 - Lisätty uudet hahmot: Tikkujäbä, Luujäbä, Mailajäbä, Isojäbä, Rynnäkköjäbä, Lepakkojäbä, Piikkipallojäbä, Muskelijäbä, Päällikköjäbä ja Juuso.
 - Lisätty HUD, joka näyttää pelaajan rahan ja kentällä olevat hahmot.
 - Lisätty Valikko, joka aukeaa klikkamalla rakennelmaa.
	- Oman rakennelman valikosta voi:
		- Kouluttaa lisää joukkoja
		- Päivittää rakennelman (ei vielä vaihtoehtoja)
	- Vihollisen rakennelman valikosta voi:
		- Hyökätä rakennelmaan
 - Pelin voi voittaa tuhoamalla vihollisten rakennelmat.

### Esiversio 14_9_2026
 - 4 tiimiä: pelaaja ja 3 bottia.
 - Tiimit merkitty väreillä: punainen, sininen, vihreä, keltainen (Todo: Värin/tiimin valitseminen).
 - Jokaisella joukkueella on alussa 1 Torni ja 1 taistelija.
 - Pelaaja voi klikata vihollisten rakennelmia ja lähettää joukkonsa tuhoamaan niitä.
 
### Esiversio 10_9_2026
 - Lisätty rakennelmat

### Esiversio 9_9_2026
 - Simppeli OpenGL-pohjaisen pelimoottorin implementaatio.
 - Taustan ja pelihahmojen renderöinti sekä äänen toistaminen.
 - Hahmo, jota voi liikuttaa.