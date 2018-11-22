package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface Bookkeeping {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
