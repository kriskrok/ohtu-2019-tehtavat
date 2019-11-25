package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "irtokipinäpussi", 15));

        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "makaroni", 45));
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla () {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 15);
    }

    @Test
    public void ostosKahdellaEriTuotteellaVeloitetaanOikein() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // maitoa
        k.lisaaKoriin(2);     // irtokipinöitä
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 20);
    }

    @Test
    public void ostosUseampiSamaaTuotettaVeloitetaanOikein() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // maitoa
        k.lisaaKoriin(1);     // lisää maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
    }

    @Test
    public void ostosEiVeloitaTuotteestaJonkaVarastoSaldoOnNolla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // maitoa
        k.lisaaKoriin(3);     // makaronia joka on loppu
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
}
