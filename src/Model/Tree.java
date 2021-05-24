package Model;

import java.util.ArrayList;

public class Tree {
    private City city;
    private ArrayList<Neighbour> neighbours;

    public Tree(City city, ArrayList<Neighbour> neighbours) {
        this.city = city;
        this.neighbours = neighbours;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<Neighbour> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Neighbour> neighbours) {
        this.neighbours = neighbours;
    }
}
