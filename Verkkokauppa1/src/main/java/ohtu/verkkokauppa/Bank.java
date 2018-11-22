package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface Bank {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    ArrayList<String> getTapahtumat();
    
}
