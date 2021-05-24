package Model;

public class Neighbour {
    private Tree neighbour;
    private int distance;
    private int time;

    public Neighbour(Tree city, int distance, int time) {
        this.neighbour = city;
        this.distance = distance;
        this.time = time;
    }

    public Tree getCity() {
        return neighbour;
    }

    public void setCity(Tree city) {
        this.neighbour = city;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
