package ohtu.verkkokauppa;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private Warehouse varasto;
    private Bank pankki;
    private Ostoskori ostoskori;
    private ReferenceGenerator viitegeneraattori;
    private String kaupanTili;

    @Autowired
    public Kauppa(Warehouse warehouse, Bank bank, ReferenceGenerator refGen) {
        varasto = warehouse;
        pankki = bank;
        viitegeneraattori = refGen;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id) > 0) {
            Tuote t = varasto.haeTuote(id);
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();

        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

    public ArrayList<String> getTapahtumat() {
        return pankki.getTapahtumat();
    }

}
