# Ohjelmistotekniikka, harjoitustyö

## Miinaharava
Ohjelma luo käynnistettäessä 20x20 kaksiulotteisen taulukon johon se sijoittaa sattumanvaraisesti 30 pommia ja tämän jälkeen laskee kaikille taulukoiden arvoille luvun joka kertoo montako pommia kunkin ruudun vieressä on. Testit testaavat pommien oikean määrän sekä naapuroivien pommien laskun kaikille ruuduille.
 
 Ohjelmalla on graafinen käyttöliittymä, jossa peli toimii, mutta pommeja ei voi merkitä. Pelin voi resettaa ja jos peli pelataan läpi, ohjelma paljastaa pommit merkillä X. Jos pelaaja painaa pommia, peli paljastaa kaikkien nappien arvot.
### Dokumentaatio
[Työn suunnitelma ja määrittely](https://github.com/StunneS/ot-harjoitustyo/blob/master/dokumentointi/alustavaMaarittely.md)

[Työmäärän kirjanpito](https://github.com/StunneS/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)


## Komentorivitoiminnot

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
