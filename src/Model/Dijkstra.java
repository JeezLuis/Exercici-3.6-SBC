package Model;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {
    private City nodeDesti;
    private City nodeOrigen;
    private Graf graf;
    private ArrayList<City> nodes;
    private ArrayList<Distancia> distancies;

    public Dijkstra(Graf graf, String origen, String desti){
        this.graf = graf;
        this.nodeDesti = getNodeFromGraf(graf, desti);
        this.nodeOrigen = getNodeFromGraf(graf, origen);
        this.nodes = graf.getNodes();
        this.distancies = graf.getDistancias();
    }

    public ArrayList<City> dijkstra() {
        ArrayList<City> cami = new ArrayList<>();
        ArrayList<City> ruta = new ArrayList<>();
        float nova = 0;
        float distanciaActualFinsAdj = 0;
        int indexActual = 0;
        int indexAdj = 0;
        City actual = nodeOrigen;

        // Farem que distanciesCami estigui sincronitzat amb nodes
        // Per tant, distanciesCami[i] sera la distancia de Node Origen a node[i]
        float[] distanciesCami = inicialitzarDistanciesCami(nodeOrigen);

        // Afegim el node origen al cami
        cami.add(nodeOrigen);

        // Mentres NO Final i NO visitats tots nodes
        while ((ruta.size() != nodes.size()) && !(ruta.contains(nodeDesti.getName()))) {

            // Agafem tots els Nodes Adjacents del Node Actual
            ArrayList<City> adjs = graf.getAdjacents(actual);

            // Per cada Adjacent fem
            for (int i = 0; i < adjs.size(); i++) {

                // Si Adjacent Afagat es NO visitat
                if (!ruta.contains(adjs.get(i).getName())) {

                    // Distancia del Node Actual fins Node Adjacent
                    distanciaActualFinsAdj = graf.getDistance(actual.getName(), adjs.get(i).getName());

                    // Busquem el index del Array de distanciesCami pel NodeActual i NodeAdj
                    indexActual = returnIndexArrayNodes(actual);
                    indexAdj = returnIndexArrayNodes(adjs.get(i));

                    // Calculem nova possible distancia
                    nova = distanciesCami[indexActual] + distanciaActualFinsAdj;

                    // Comprovem si ens interessa mes aquesta nova distancia
                    if (distanciesCami[indexAdj] > nova) {
                        distanciesCami[indexAdj] = nova;
                        actualitzaCami(cami, actual);
                    }
                }
            }
            ruta.add(actual);
            actual = retornaNodeAdjMenorPes(actual, adjs, ruta);
        }

        // Afegim el node desti al cami
        actualitzaCami(cami, nodeDesti);

        return cami;
    }

    private void actualitzaCami(ArrayList<City> cami, City insertar) {
        if (!cami.contains(insertar)) {
            cami.add(insertar);
        }
    }

    private int returnIndexArrayNodes(City node) {
        for (int i = 0; i < nodes.size(); i++) {
            if (node.getName().equals(nodes.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    private float[] inicialitzarDistanciesCami(City nodeOrigen) {
        float[] distanciesCami = new float[nodes.size()];

        Arrays.fill(distanciesCami, Float.MAX_VALUE);

        ArrayList<City> adjs = graf.getAdjacents(nodeOrigen);

        for (int i = 0; i < adjs.size(); i++) {
            int posicio = returnIndexArrayNodes(adjs.get(i));
            distanciesCami[posicio] = graf.getDistance(nodeOrigen.getName(), adjs.get(i).getName());
        }

        int index = returnIndexArrayNodes(nodeOrigen);
        distanciesCami[index] = 0;

        return distanciesCami;
    }

    private City retornaNodeAdjMenorPes(City actual, ArrayList<City> adjs, ArrayList<City> ruta) {
        float minimaDistancia = Float.MAX_VALUE;
        City adj;
        City minimNode = null;
        float distanciaActualAdj;

        for (int i = 0; i < adjs.size(); i++) {
            adj = adjs.get(i);
            distanciaActualAdj = graf.getDistance(actual.getName(), adj.getName());

            if (!ruta.contains(adjs.get(i).getName())) {
                if (minimaDistancia >= distanciaActualAdj) {
                    minimNode = adj;
                    minimaDistancia = distanciaActualAdj;
                }
            }
        }

        return minimNode;
    }

    /**private float retornaDistancia(City node1, City node2) {
        Node nodeOrigenLocal;
        Node nodeDestiLocal;

        for (int i = 0; i < distancies.length; i++) {
            nodeOrigenLocal = getNodeFromGraf(graf, distancies[i].getOrigen());
            nodeDestiLocal = getNodeFromGraf(graf, distancies[i].getDesti());

            if (nodeOrigenLocal == node1 && nodeDestiLocal == node2) {
                if(node2 != nodeDesti && node2.getTipus() == Node.DANGER){
                    return distancies[i].getDistancia() * 10;
                } else {
                    return distancies[i].getDistancia();
                }
            } else if (nodeDestiLocal == node1 && nodeOrigenLocal == node2) {
                if(node2 != nodeDesti && node2.getTipus() == Node.DANGER){
                    return distancies[i].getDistancia() * 10;
                } else {
                    return distancies[i].getDistancia();
                }
            }
        }

        return -1;
    }**/

    private City getNodeFromGraf(Graf graf, String name){
        for (City node: graf.getNodes()) {
            if(node.getName().equals(name)) return node;
        }
        return null;
    }

    public void mostrarDijkstra(ArrayList<City> ints){
        System.out.println("\n[Dijkstra] Obtenint la ruta òptima...\n");
        for (int i = 0; i < ints.size() - 1; i++) {
            System.out.print(ints.get(i).getName() + " -> ");
        }
        System.out.print(ints.get(ints.size() - 1).getName());
        System.out.println(" ");
    }
}


