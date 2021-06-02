import AEstrellaGraph.AStar;
import Model.City;
import Model.Dijkstra;
import Model.Graf;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ImportData id = new ImportData("Data/spain_routes.json");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Origen: ");

        String from = scanner.nextLine();

        System.out.println("Desti: ");

        String to = scanner.nextLine();

        boolean existeix = false;

        for (City c: id.getGraf().getNodes()) {
            if(c.getName().equals(from)) existeix = true;
        }

        if (!existeix) System.out.println(from + " no es una ciutat registrada.");
        else {

            existeix = false;

            for (City c: id.getGraf().getNodes()) {
                if(c.getName().equals(to)) existeix = true;
            }

            if (!existeix) System.out.println(to + " no es una ciutat registrada.");
            else {

                //new AEstrella(id.getGraf(), from, to);

                City cityFrom = null;
                City cityTo = null;

                for (City aux : id.getGraf().getNodes()){

                    if (aux.getName().equals(from)){
                        cityFrom = aux;
                    }

                    if (aux.getName().equals(to)){
                        cityTo = aux;
                    }

                }

                AStar aStar = new AStar(id.getGraf(), cityFrom , cityTo);
                aStar.startAStar();
                aStar.showRoute();


                System.out.println("Selecciona per on començar a pintar:");
                System.out.println("Origen: ");

                String origen = scanner.nextLine();
                City origin = null;

                existeix = false;

                for (City c: id.getGraf().getNodes()) {
                    if(c.getName().equals(origen)) {
                        existeix = true;
                        origin = c;
                    }
                }

                if (!existeix) System.out.println(from + " no es una ciutat registrada.");
                else {
                    //new CSP(id.getGraf(), origin);
                }

                /*
                new CSP(id.getGraf(), from, to);

                Dijkstra dijkstra = new Dijkstra(id.getGraf(), from, to);

                ArrayList<City> text = dijkstra.dijkstra();

                dijkstra.mostrarDijkstra(text);

                */

            }

        }

    }

}
