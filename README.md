# Rytäkkä

### Peli, jossa rytisee

## Kuvaus

Tässä pelissä taistelet kolmea bottia vastaan. Ne luovat joukkoja, jotka sinun on omilla joukoillasi päihitettävä. Voit joukkojen lisäksi rakentaa kultakaivoksia, jotka tuottavat sinulle enemmän rahaa. Päihitettyäsi kaikki botit voitat pelin!

## Pelaaminen

Tarvitset peliä varten Android-puhelimen. Pelissä on päävalikko, josta voit aloittaa pelin. Voit myös keskeyttää pelin ja palata päävalikkoon. Peli käynnistetään esim. Android Studion kautta.
Puhelin tulee yhdistää tietokoneeseen, jossa on Android Studio tai vastaava alusta asennettuna. Tällöin alusta voi löytää puhelimen ja sillä voi pelata peliä. Puhelimen asetuksista tulee sallia kehittäjäasetukset.
Peliä voi pelata myös Android-emulaattorilla. Paras kokemus tulee kuitenkin puhelimella.

## Lisenssi

Projektin lähdekoodi on lisensoitu MIT-lisenssillä.
ks. `LICENSE`-tiedosto
Kolmansien osapuolten ominaisuudet eivät kuulu lisenssin alle ja niihin sovelletaan niiden omia lisenssejä sekä käyttöehtoja.

## Sovelluksensisäiset ostokset

Sovelluksessa käytetään RevenueCatia sovelluksensisäisiä ostoja varten.
Lepakkomies-skinin saa RevenueCatin entitlementin kautta.

## Teknologiat

 - Java
 - Android
 - OpenGL ES
 - RevenueCat

## Testaus

Peli on pelitestattu ja ominaisuuksien testaamiseen on käytetty Android Studion Logcat-logeja logaten olennaisia arvoja sekä mahdollisia virheviestejä. Testaamiseen on käytetty monta eri Android-laitetta.

## Asennus

Asennusta varten ei nykyisellään ole alustaa eikä työkaluja. Pelin voi ajaa esim. Android Studiosta.

## Tuki

Kehittäjiin voi ottaa yhteyttä.

## Kehittäjät

Joonatan Taurio & Lenni Sigfridsson

## Tulevat toiminnot

ks. versiolistaus alla

## Dokumentaatio

Tulossa.

## Versiolistaus

## Uusin versio (1.10.2026): 0.5

#### **Todo (projektiversio): **
 - Viimeistely
 
#### **Todo (tulevaisuuden versioihin): **
 - Tiimien hallinta: Pelaaja voi valita tiimin värin/joukkueen. Hahmot voidaan värikoodata esim. shaderin avulla.
 - Ostovalikkoon hahmojen nimet, hinnat ja indikaattori siihen, onko varaa (muuta harmaaksi jos ei).
 - Slotteja kentälle, joihin pelaaja ja viholliset voivat rakentaa rakennelmiaan.

### Versio 0.5
 - Lisätty boteille tekoäly.
 - Kommentoitu ja dokumentoitu koodi.
 - Lepakkojäbä täytyy nyt ostaa.

### Versio 0.4
 - Lisätty RevenueCat ja pelinsisäiset ostot.
 
### Versio 0.3
 - Lisätty valikot: päävalikko, pause-valikko, ohjeikkuna ja kehittäjäikkuna.
 - Lisätty mahdollisuus ostaa kaivoksia, jotka tuottavat rahaa.
 - Lisätty statsit pelihahmoille ja rakennelmille.
 
### Versio 0.2
 - Lisätty vihollisten hahmoihin hyökkääminen. Hahmot puolustavat myös aina omaa rakennelmaa.
 - Lisätty passiivinen rahantulo.
 - Lisätty elämäpalkki rakennelmille.

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