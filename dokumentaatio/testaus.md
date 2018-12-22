## Testausdokumentti
Ohjelmaa on testattu automatisoiduilla testeillä JUnitin avulla, sekä manuaalisesti järjestelmätasolla.
### Sovelluslogiikka
Automatisoidut testit [GridTest](https://github.com/StunneS/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/GridTest.java) ja [ScoreTest](https://github.com/StunneS/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/ScoreTest.java) testavat pakkauksen miinaharava.com.mycompany.miinaharava luokkia, joista Grid on pohjana koko ohjelman toiminnalle ja Score luokkaa tarvitaan arvojen lisäämiseen tietokantaan.

### DAO-luokka
ScoreDao luokan toimintaa testataan hyödyntämällä testitietokantaa test.db, joka testatessa luodaan jos sitä ei ole vielä olemassa.
### Testauskattavuus
Käyttöliittymäkansiota lukuunottamatte sovelluksen testauksen rivikattavuus on 75% ja haaraumakattavuus 79%
![](https://raw.githubusercontent.com/StunneS/ot-harjoitustyo/master/laskarit/Kattavuus.png)
Testaamatta jäi lähes kokonaan luokka [ScoreKeeper](https://github.com/StunneS/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/com/mycompany/miinaharava/ScoreKeeper.java) sillä testejä suorittaessa ScoreKeeper käynnistää [ScoreBoard](https://github.com/StunneS/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/guifx/ScoreBoard.java) luokan olion joka aiheutti virhetilanteen jota en saanut ratkaistua.

## Järjestelmätestaus
Järjestelmätestaus on suoritettu manuaalisesti.
### Asennus
Sovelluksen .jar version toimivuutta on testattu windowsilla.

Sovellusta on testattu sekä tilanteissa, joissa tietokanta on ollut olemassa ja jolloin sitä ei ole ollut jolloin ohjelma on luonut sen itse.
## Sovellukseen jääneet laatuongelmat
ScoreKeeper -luokkaa ei voi testata.
