# Ohjelmistotekniikka, harjoitustyö

## Miinaharava
Ohjelma luo käynnistettäessä 16x16 kaksiulotteisen taulukon johon se sijoittaa sattumanvaraisesti 40 pommia ja tämän jälkeen laskee kaikille taulukoiden arvoille luvun joka kertoo montako pommia kunkin ruudun vieressä on. Testit testaavat pommien oikean määrän sekä naapuroivien pommien laskun kaikille ruuduille.
 
 Ohjelmalla on graafinen käyttöliittymä, jossa peli toimii. Pelissä voi merkata mahdollisia pommeja lipuilla oikealla hiiren painekkeella. Pelin voi resettaa ja jos peli pelataan läpi, ohjelma paljastaa pommien paikat ja antaa "Victory!" -tekstin. Jos pelaaja painaa pommia, peli paljastaa kaikkien nappien arvot, ja näyttää painetun pommin punaisella rastilla.

Pelissä on kolme valmiiksi määriteltyä kokoa, easy(8x8) jossa 10 pommia, medium(16x16) jossa 40 pommia sekä hard(24x24) jossa on 99 pommia.

### Realeaset

[Viikon 5 release](https://github.com/StunneS/ot-harjoitustyo/releases/tag/viikko5)

[Viikon 6 release](https://github.com/StunneS/ot-harjoitustyo/releases/tag/viikko6)

### Dokumentaatio
[Vaatimusmäärittely](https://github.com/StunneS/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työmäärän kirjanpito](https://github.com/StunneS/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

[Työn arkkitehtuuri](https://github.com/StunneS/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

### Käynnistys

Ohjelman saa käynnistettyä komennolla

```
mvn compile exec:java -Dexec.mainClass=guifx.GUIJavaFX
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_


### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
