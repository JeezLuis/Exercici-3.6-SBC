package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Graf {
    private HashMap<City, ArrayList<City>> llistaAdjacencia;
    private HashMap<City, Integer> depth;
    private ArrayList<Distancia> distancias = new ArrayList<>();

    private City [] aux;

    public Graf(City [] nodes, Distancia[] distancias) {
        this.aux = nodes;
        llistaAdjacencia = new HashMap<>();
        creaGraf(nodes, distancias);
        this.distancias.addAll(Arrays.asList(distancias));
        resetDepth();
    }

    private void creaGraf(City[] nodes, Distancia[] distancies) {
        City origen = null;
        City desti = null;
        for (int i = 0; i < distancies.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                if (nodes[j].getName().equals(distancies[i].getOrigin())) {
                    origen = nodes[j];
                    break;
                }
            }

            for (int j = 0; j < nodes.length; j++) {
                if (nodes[j].getName().equals(distancies[i].getDestination())) {
                    desti = nodes[j];
                    break;
                }
            }

            afegirAdjacent(origen, desti);
            afegirAdjacent(desti, origen);

        }

    }

    private void afegirAdjacent(City origen, City adj) {
        if (!llistaAdjacencia.containsKey(origen)) {
            ArrayList<City> nodesAdjacents = new ArrayList<>();
            nodesAdjacents.add(adj);
            llistaAdjacencia.put(origen, nodesAdjacents);
        } else {
            ArrayList<City> nodesAdjacents = llistaAdjacencia.get(origen);
            if (!nodesAdjacents.contains(adj)) {
                nodesAdjacents.add(adj);
                llistaAdjacencia.remove(origen);
                llistaAdjacencia.put(origen, nodesAdjacents);
            }
        }
    }

    public ArrayList<City> getNodes() {
        return new ArrayList<>(llistaAdjacencia.keySet());
    }

    public ArrayList<City> getAdjacents(City node) {
        return llistaAdjacencia.get(node);
    }

    public Graf(Graf g) {
        llistaAdjacencia = new HashMap<>();
        ArrayList<City> adjs;
        ArrayList<City> adjsCopia = new ArrayList<>();
        for (int i = 0; i < g.getNodes().size(); i++) {
            adjs = g.getAdjacents(g.getNodes().get(i));
            adjsCopia = (ArrayList<City>) adjs.clone();
            llistaAdjacencia.put(g.getNodes().get(i), adjsCopia);
        }
    }

    public long getDistance(String origin, String destination){
        for (int i = 0; i < distancias.size(); i++) {
            if(distancias.get(i).getOrigin().equals(origin) && distancias.get(i).getDestination().equals(destination)){
                return distancias.get(i).getDistance();
            }
        }
        return -1;
    }

    public long getDuration(String origin, String destination){
        for (int i = 0; i < distancias.size(); i++) {
            if(distancias.get(i).getOrigin().equals(origin) && distancias.get(i).getDestination().equals(destination)){
                return distancias.get(i).getDuration();
            }
        }
        return -1;
    }

    public void resetDepth(){
        depth = new HashMap<>();

        for (City n: aux){
            depth.put(n, -1);
        }
    }

    public int getDepth(City city){
        return depth.get(city);
    }

    public void setDepth(City city, int value){
        depth.replace(city, value);
    }
}
