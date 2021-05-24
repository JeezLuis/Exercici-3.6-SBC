package Model;

public class Distancia {
    private String origin;
    private String destination;
    private long distance;
    private long duration;

    public Distancia(String origin, String destination, long distance, long duration) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
