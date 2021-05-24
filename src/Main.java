import Model.City;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ImportData id = new ImportData("Data/spain_routes.json");

        //TODO: Demanar al usuari de on surt i on va

        String from = "Barcelona";
        String to = "Madrid";

        new AEstrella(id.getGraf(), from, to);

        new CSP(id.getGraf(), from, to);


    }

}
