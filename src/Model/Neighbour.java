package Model;

public class Neighbour {
    private City city;
    private int distance;
    private int time;

    public Neighbour(City city, int distance, int time) {
        this.city = city;
        this.distance = distance;
        this.time = time;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
