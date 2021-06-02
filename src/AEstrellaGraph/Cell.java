package AEstrellaGraph;

import Model.City;

import java.util.ArrayList;

public class Cell {

    protected City city;
    private long fValue = Long.MAX_VALUE;
    private long hValue;
    private long gValue = Long.MAX_VALUE;
    protected Cell parent;
    protected ArrayList<Connection> connections;
    protected boolean isStartCell = false;


    public Cell(City city, double endLat, double endLong){

        connections = new ArrayList<>();
        this.city = city;
        calculateHeuristic(endLat, endLong);
        System.out.println("EURISTIC calculated: " + hValue);

    }

    public void setAsStartCell(){

        gValue = 0;
        isStartCell = true;

    }


    //We recalculate the g and f values
    public void evaluate(Cell parent, Connection connection){

        long auxGValue = parent.getG() + connection.length;

        if (auxGValue < gValue){
            this.parent = parent;
            gValue = auxGValue;
        }
        getF();

        System.out.println("\t\t\t\tRecalculated values: G: " + gValue + "   F: " + fValue);

    }

    private void calculateHeuristic(double endLat, double endLong){

        hValue = (long) StarUtilities.distance(city.getLatitude(), city.getLongitude(), endLat, endLong, 'K');

    }

    public long getF(){

        fValue = hValue + gValue;

        return fValue;
    }

    public long getG(){
        return gValue;
    }

}
