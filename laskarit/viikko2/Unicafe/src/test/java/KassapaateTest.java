/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sade-Tuuli
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void hello() {}
    
    @Test
    public void rahamaaraOikea() {
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void maukkaitaMyytyNolla() {
        assertEquals(0,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisiaMyytyNolla() {
        assertEquals(0,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void edullisestiMaaraKasvaaKateisella() {
        paate.syoEdullisesti(500);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaastiMaaraKasvaaKateisella() {
        paate.syoMaukkaasti(500);
        assertEquals(1,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisestiKassaKasvaaKateisella() {
        paate.syoEdullisesti(500);
        assertEquals(100240,paate.kassassaRahaa());
    }
    @Test
    public void maukkaastiKassaKasvaaKateisella() {
        paate.syoMaukkaasti(500);
        assertEquals(100400,paate.kassassaRahaa());
    }
    @Test
    public void edullisestiVaihtorahaOikeaKateisella() {
        assertEquals(260,paate.syoEdullisesti(500));
    }
    @Test
    public void maukkaastiVaihtorahaOikeaKateisella() {
        assertEquals(100,paate.syoMaukkaasti(500));
    }
    @Test
    public void edullisestiMaaraEiKasvaKateisella() {
        paate.syoEdullisesti(100);
        assertEquals(0,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaastiMaaraEiKasvaKateisella() {
        paate.syoMaukkaasti(300);
        assertEquals(0,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisestiKassaEiKasvaKateisella() {
        paate.syoEdullisesti(100);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void maukkaastiKassaEiKasvaKateisella() {
        paate.syoMaukkaasti(300);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void edullisestiVaihtorahaOikeaLiianPienellaKateisella() {
        assertEquals(100,paate.syoEdullisesti(100));
    }
    @Test
    public void maukkaastiVaihtorahaOikeaLiianPienellaKateisella() {
        assertEquals(100,paate.syoMaukkaasti(100));
    }
    @Test
    public void edullisestiMaaraKasvaaKortilla() {
        paate.syoEdullisesti(kortti);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaastiMaaraKasvaaKortilla() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisestiKassaEiIkinaKasvaaKortilla() {
        paate.syoEdullisesti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void maukkaastiKassaEiIkinaKasvaaKortilla() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void edullisestiTotuusarvoOikeaKortilla() {
        assertEquals(true,paate.syoEdullisesti(kortti));
    }
    @Test
    public void maukkaastiTotuusarvoOikeaKortilla() {
        assertEquals(true,paate.syoMaukkaasti(kortti));
    }
    @Test
    public void edullisestiMaaraEiKasvaKortilla() {
        kortti.otaRahaa(900);
        paate.syoEdullisesti(kortti);
        assertEquals(0,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaastiMaaraEiKasvaKortilla() {
        kortti.otaRahaa(900);
        paate.syoMaukkaasti(kortti);
        assertEquals(0,paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisestiKassaEiKasvaKortilla() {
        kortti.otaRahaa(900);
        paate.syoEdullisesti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void maukkaastiKassaEiKasvaKortilla() {
        kortti.otaRahaa(900);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void edullisestiTotuusarvoOikeaLiianPienellaSaldolla() {
        kortti.otaRahaa(900);
        assertEquals(false,paate.syoEdullisesti(kortti));
    }
    @Test
    public void maukkaastiTotuusarvoOikeaLiianPienellaSaldolla() {
        kortti.otaRahaa(900);
        assertEquals(false,paate.syoMaukkaasti(kortti));
    }
    @Test
    public void edullisestiKortinSaldoEiMuutuPienellaSaldolla() {
        kortti.otaRahaa(900);
        paate.syoEdullisesti(kortti);
        assertEquals(100,kortti.saldo());
    }
    @Test
    public void maukkaastiKortinSaldoEiMuutuPienellaSaldolla() {
        kortti.otaRahaa(900);
        paate.syoMaukkaasti(kortti);
        assertEquals(100,kortti.saldo());
    }
    @Test
    public void kortilleLataaminenMuuttaaSaldoa() {
        paate.lataaRahaaKortille(kortti, 200);
        assertEquals(1200,kortti.saldo());
    }
    @Test
    public void kortilleLataaminenMuuttaaKassaa() {
        paate.lataaRahaaKortille(kortti, 200);
        assertEquals(100200,paate.kassassaRahaa());
    }
    @Test
    public void kortilleNegatiivisenLataaminenEiMuutaSaldoa() {
        paate.lataaRahaaKortille(kortti, -200);
        assertEquals(1000,kortti.saldo());
    }
    /*
    kortille rahaa ladattaessa kortin saldo muuttuu ja kassassa oleva rahamäärä kasvaa ladatulla summalla
    */
}
