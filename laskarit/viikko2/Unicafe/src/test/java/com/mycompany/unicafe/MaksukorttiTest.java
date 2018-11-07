package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void lisaaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        kortti.lataaRahaa(30);
        assertEquals("saldo: 1.40", kortti.toString());
    }
    @Test
    public void rahanOttoJosRahaaTarpeeksi() {
        kortti.lataaRahaa(110);
        kortti.otaRahaa(30);
        assertEquals("saldo: 0.90", kortti.toString());
    }
    @Test
    public void rahanOttoJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(30);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void rahanOttoFalseJosRahaaEiTarpeeksi() {
        assertEquals(false, kortti.otaRahaa(30));
    }
    @Test
    public void rahanOttoTrueJosRahaaTarpeeksi() {
        assertEquals(true, kortti.otaRahaa(10));
    }
    @Test
    public void rahanSaldoOnOikein () {
        kortti.lataaRahaa(200);
        assertEquals(210,kortti.saldo());
    }
    
}
