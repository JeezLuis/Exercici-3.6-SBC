import Model.City;
import Model.Graf;

import java.util.ArrayList;

public class CSP {

    private String[] colors = {"Blau", "Vermell", "Negre", "Verd"};

    private ArrayList<CityColor> final_list;

    public CSP(Graf graf, City start) {
        colorProblem(graf, start);
    }

    public void colorProblem(Graf graf, City start_point){
        final_list = new ArrayList<>();

        final_list.add(new CityColor(start_point, ""));

        while (graf.getNodes().size() != final_list.size()){

            CityColor current = null;

            for (CityColor cc: final_list) {
                if (cc.getColor().equals("")){
                    current = cc;
                    for (String s: colors) {
                        boolean es_fa_servir = false;
                        for (CityColor cc_aux: final_list) {
                            for (City adj: graf.getAdjacents(cc.getCity())) {
                                if (cc_aux.getCity().equals(adj) && cc.getColor() == s) es_fa_servir = true;
                            }
                        }
                        if (!es_fa_servir){
                            cc.setColor(s);
                        }
                    }
                }
            }
            for (City c: graf.getAdjacents(current.getCity())) {
                for (CityColor aux : final_list) {
                    if (!aux.getCity().equals(c)) final_list.add(new CityColor(c,""));
                }
            }
        }

        for (CityColor cc : final_list) {
            System.out.println(cc.getCity().getName() + " -> " + cc.getColor());
        }

    }

    private static class CityColor{
        private City city;
        private String color;

        public CityColor(City city, String color) {
            this.city = city;
            this.color = color;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

}


