## Rakenne
Ohjelman rakenne koostuu kolmesta pakkauksesta, joiden pakkausrakenne on seuraavanlainen: 

![](https://raw.githubusercontent.com/StunneS/ot-harjoitustyo/master/laskarit/Rakenne.png)

Pakkaus miinaharava.guifx sisältää ohjelman käyttöliittymän, joka on luotu JavaFX:llä.
Pakkaus miinaharava.com.mycompany.miinaharava sisältää ohjelman sovelluslogiikan.
Pakkaus miinaharava.database sisältää tietojen pysyväistallennukseen vaadittavan koodin.

## Käyttöliittymä

Käyttöliittymässä on kaksi erillistä näkymää, peliruutu ja uuden pistetuloksen lisäys. Molemmat näkymät ovat omat Scene -olionsa. Käyttöliittymä on rakennettu luokkaan miinaharava.guifx.

Käyttöliittymä on eritelty sovelluuslogiikasta mahdollisimman hyvin mutta pelin voittoehtojen tarkistaminen tapahtuu osittain käyttöliittymässä sillä siihen vaikuttaa nappien id:iden arvot, joita ei voi tarkistaa muualta kun itse käyttöliittymästä. Sovelluslogiikka hoidetaan muuten kutsumalla luokan Grid metodeja.

Peliruutu on pelin aikana aina auki, ja uuden pistetuloksen lisäys käynnistyy hetkellisesti kun peli on pelattu läpi siten että suoritusaika on tarpeeksi hyvä ja tulos pääsee kyseisen vaikeustason kymmenne parhaan tulosen joukkoon. Kun käyttäjä on syöttänyt nimensä niin tulos tulee näkyviin listalle kun painetaan reset -nappia tai kyseistä tasoa vastaavaa nappia.

## Luokkakaavio ja sovelluslogiikka

Ohjelman luokkakaavio on kyseinen:

![](https://raw.githubusercontent.com/StunneS/ot-harjoitustyo/master/laskarit/Luokkakaavio.png)

Ohjelman pelilaudan muodostamisesta vastaa luokka Grid, joka luo parametrein annetun kokoisen kaksiulotteisen taulukkon ja huolehtii pommien sijoittamisesta sekä ruutuja naapuroivien pommien määrän laskemisesta. Grid luokka säilyttää kyseiset tiedot, joita käyttöliittymä hyödyntää pelin näkymän pitämiseen oikeanlaisena.

Graafinen käyttöliittymä guiJavaFX huolehtii pelin visuaalisesta toiminnasta sekä pelin uudelleenkäynnistämisestä ja näyttää parhaat tulokset. Pelin sääntöjen läpikäynti sekä pelin erikoistoimintojen suoritus tapahtuu käyttöliittymässä yhteistyössä Grid -luokan metodien (esim. adjacentZeros(int x, int y),getNeighbors(int x, int y)) kanssa.

Luokka TimeCounter on laajennettu versio teksti-ikkunasta (Pane) joka näkyy käyttöliittymässä ja joka kertoo sekunttimäärän jossa peli on pelattu läpi. Sen arvoa hyödynnetään uuden tuloksen tallentamisessa.

Luokka ScoreBoard on laajennettu versio VBox:ista, joka näyttää tietyn vaikeustason parhaat tulokset. Se näkyy graafisessa käyttöliittymässä, mutta sen muokkaamisesta huolehtii luokka ScoreKeeper.

ScoreBoardin päivittämisen lisäksi luokka ScoreKeeper huolehtii myös tulosten lisäämiskutsuista ScoreDao:ihin.

ScoreDao toteuttaa rajapinnan Dao, ja sen metodeja kutsutaan kun tietoa laitetaan ja haetaan tietokannasta.

## Tietojen pysyväistallennus
Pakkauksessa miinaharava.database sijaitseva luokka ScoreDao huolehtii uusien Score -olioiden tallentamisesta tietokantaan. Luotava tietokanta on nimeltään "scores.db" mutta se on muutettavissa.

Koska testaus halutaan suorittaa ilman että oikeaan tietokantaan vaikutetaan, testauksessa hyödynnetään testitietokantaa "test.db".

### Tietokanta

Sovellus tallentaa tietokantaan pelisuorituksen tekijän nimen sekä sekunttimäärän jossa suoritus on tehty. Joksaisella pelin vaikeusasteellaan on oma tietokantataulu, mutta ne sijaitsevat samassa tietokannassa. 

Sovellus tallentaa tuloksen muodossa

<pre>
(int id, String nimi, int sekunttimäärä)
</pre>

jossa id:n arvon määrää tietokanta itse.

## Päätoiminnallisuudet

Tässä on kuvattuna sovelluksen toimintalogiikkaa muutamissa ohjelman toiminnan kannalta tärkeissä kohdissa sekvenssikaavioina.

### Nolla pomminaapuria omaavan napin painallus

Kun pelissä painetaan nappia jolla on nolla pommia naapureina, ohjelma aukaisee kyseisen napin ympärillä olevista napeista kaikki ne joiden arvo on nolla tai jotka sijaitsevat nollan vieressä. Tapahtuma etenee ohjelmassa seuraavasti:

![](https://raw.githubusercontent.com/StunneS/ot-harjoitustyo/master/laskarit/Button%20press%20on%20a%20button%20that%20has%20zero%20on%20it%20(1).png)

Tässä oletettiin että peli ei täyttänyt voiton ehtoja ja metodi pysähtyi.

### Reset -napin painaminen

Reset -napin painaminen aiheuttaa pelilaudan uudelleenkäynnistämisen, johon kuuluu pommien uudelleensijoittaminen ja ajastimen nollaaminen. Oletetaan että uudelleenkäynnistettävä taso on easy.

![](https://raw.githubusercontent.com/StunneS/ot-harjoitustyo/master/laskarit/PressReset.png)

Metodia updateScoreboard() ei näy kokonaan sekvenssikaaviossa mutta sen toiminta ei ole tärkeä tässä kuvauksessa. 

### Arvon lisääminen tietokantaan

Pelin voittaminen aiheuttaa sen että kaikki ruudukon arvot paljastetaan ja jos suorituksen aika on tarpeeksi hyvä, se lisätään tietokantaan. Sekvenssikaavio alkaa siitä kun ohjelma on todennut että peli on voitettu.

![](https://raw.githubusercontent.com/StunneS/ot-harjoitustyo/master/laskarit/AddingScoreToDb.png)

### Muut toiminnallisuudet

Vaikeustason vaihtamisen napit toimivat lahes samalla tavalla kuin reset-nappi,ja jos peli voitetaan mutta arvo ei ole tarpeeksi hyvä ohjelma ei laukaise voitto ruutua. Pelin häviö toimii siten että ohjelma katsoo että painettu nappi on pommi ja pysäyttää pelin.

## Ohjelmaan jääneet heikkoudet

Graafisessa käyttöliittymässä tapahtuu vielä pelilogiikkaan liittyviä tarkistuksia, kuten voiton tarkastus. Ohjelma ei myöskään näytä heti arvoa joka on lisätty tietokantaan, eikä kaikkia arvoja ole mahdollista nähdä, tai poistaa käyttäjän käskystä.
