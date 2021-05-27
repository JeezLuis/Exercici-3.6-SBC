import Model.City;
import Model.Graf;

import java.util.ArrayList;

public class AEstrella {

    public AEstrella(Graf graf, String from, String to) {
        boolean trobat = false;
        boolean primera = true;

        City ciutat_origen = getNodeFromGraf(graf, from);
        City ciutat_desti = getNodeFromGraf(graf, to);

        graf.setDepth(ciutat_origen, 0);

        City minimum = ciutat_origen;

        City current = ciutat_origen;


        graf.resetDepth();

        ArrayList<City> senseVisitar = new ArrayList<>(graf.getAdjacents(ciutat_origen));

        while (senseVisitar.size() > 0){
            City next = senseVisitar.get(0);
            graf.setDepth(next, (int) (graf.getDepth(current) + graf.getDistance(current.getName(),next.getName())));

            current = next;

            System.out.println("Opcions:");
            for (City c: senseVisitar) {
                System.out.println(c.getName());
            }

            if (current == ciutat_desti){
                System.out.println("He arribat!");
                trobat = true;
                break;
            }
            else{

                senseVisitar.remove(current);
                for (City c: graf.getAdjacents(current)){
                    if (c != ciutat_origen){
                        senseVisitar.add(c);
                        graf.setDepth(c, (int) (graf.getDepth(current) + graf.getDistance(current.getName(),c.getName())));
                    }

                }

                if (graf.getDepth(current) < graf.getDepth(minimum) || primera){
                    System.out.println("Es "+current.getName()+" el millor cami? SI");
                    minimum = current;
                }
                else{
                    System.out.println("Es "+current.getName()+" el millor cami? NO");
                }
                System.out.println("\n\n");
            }
            primera = false;
        }
    }

    private City getNodeFromGraf(Graf graf, String name){
        for (City node: graf.getNodes()) {
            if(node.getName().equals(name)) return node;
        }
        return null;
    }
}
