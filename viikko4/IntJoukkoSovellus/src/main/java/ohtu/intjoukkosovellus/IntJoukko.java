
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasiteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kasvatuskoko ei voi olla negatiivinine");
        }
        ljono = new int[kapasiteetti];

        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private void kasvataTaulukkoa() {
        int[] uusi = new int[ljono.length + OLETUSKASVATUS];
        for (int i = 0; i < ljono.length; i++) {
            uusi[i] = ljono[i];
        }
        this.ljono = uusi;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }

        if (alkioidenLkm % OLETUSKASVATUS == 0) {
            kasvataTaulukkoa();
        }

        for (int i = 0; i < ljono.length; i++) {
            if (ljono[i] < luku) {
                continue;
            }
            int apu = ljono[i];
            ljono[i] = luku;
            luku = apu;
        }

        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;

        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < ljono.length; i++) {
            if (ljono[i] == luku) {
                return true;
            }
        }
        return false;
    }

    private int etsiLuvunIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int indeksi = etsiLuvunIndeksi(luku);
        if (indeksi == -1) {
            return false;
        }

        for (int i = indeksi; i < alkioidenLkm -1; i++) {
            ljono[i] = ljono[i+1];
        }
        alkioidenLkm--;
        return true;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        if (alkioidenLkm == 1) {
            sb.append(ljono[0]);
        }

        if (alkioidenLkm > 1) {
            sb.append(ljono[0]).append(",");

            for (int i = 1; i < alkioidenLkm; i++) {
                if (i == alkioidenLkm -1) {
                    sb.append(" ").append(ljono[i]);
                } else {
                    sb.append(" ").append(ljono[i]).append(",");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public int[] toIntArray() {
        int[] intArray = new int[alkioidenLkm];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = ljono[i];
        }
        return intArray;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] joukkoB = b.toIntArray();
        for (int i = 0; i < joukkoB.length; i++) {
            a.lisaa(joukkoB[i]);
        }
        return a;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] joukkoA = a.toIntArray();

        for (int i = 0; i < joukkoA.length; i++) {
            if (b.kuuluu(joukkoA[i])) {
                y.lisaa(joukkoA[i]);
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        int[] joukkoB = b.toIntArray();
        for (int i = 0; i < joukkoB.length; i++) {
            a.poista(joukkoB[i]);
        }
        return a;
    }
        
}
