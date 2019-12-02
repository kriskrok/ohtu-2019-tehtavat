package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    protected int edellinenArvo;
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;


    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.edellinenArvo = 0;
    }

    public abstract void suorita();

    public void peru() {
        sovellus.setTulos(edellinenArvo);
        paivitaNaytto();
        undo.disableProperty().set(true);
    };

    protected int lueLuku() {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception erhe) {
            System.err.println(erhe.getMessage());
        }

        return arvo;
    }

    protected void paivitaNaytto() {
        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        if (laskunTulos == 0) nollaa.disableProperty().set(true);
        else nollaa.disableProperty().set(false);

        undo.disableProperty().set(false);
    }

    protected void talletaNykyinenTulos() {
        this.edellinenArvo = sovellus.tulos();
    }
}
