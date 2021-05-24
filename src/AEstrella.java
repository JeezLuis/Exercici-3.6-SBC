import Model.City;
import Model.Graf;

import java.util.ArrayList;

public class AEstrella {

    public AEstrella(Graf graf, String from, String to) {
        boolean trobat = false;

        City ciutat_origen = getNodeFromGraf(graf, from);
        City ciutat_desti = getNodeFromGraf(graf, to);

        ArrayList<City> senseVisitar = new ArrayList<>(graf.getAdjacents(ciutat_origen));

        while (senseVisitar.size() > 0){
            City current = senseVisitar.get(0);
            if (current == ciutat_desti){
                trobat = true;
                break;
            }
            else{
                senseVisitar.remove(current);
            }
        }
    }

    private City getNodeFromGraf(Graf graf, String name){
        for (City node: graf.getNodes()) {
            if(node.getName().equals(name)) return node;
        }
        return null;
    }
}
