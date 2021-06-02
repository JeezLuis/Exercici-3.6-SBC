package AEstrellaGraph;

import Model.City;
import Model.Distancia;
import Model.Graf;
import java.util.ArrayList;

public class AStar {

    private ArrayList<Cell> allCells;
    private ArrayList<Cell> open;
    private ArrayList<Cell> closed;

    private Cell start;
    private Cell end;

    private boolean routeCalculated = false;

    public AStar(Graf graf, City start, City end){

        open = new ArrayList<>();
        closed = new ArrayList<>();
        allCells = new ArrayList<>();

        for (City city : graf.getNodes()){

            Cell auxCell = new Cell(city, end.getLatitude(), end.getLongitude());

            if (city.getName().equals(start.getName())){

                this.start = auxCell;
                auxCell.setAsStartCell();

            }

            if (city.getName().equals(end.getName())){
                this.end = auxCell;
            }

            allCells.add(auxCell);

        }

        for (Distancia rawConnection : graf.getDistancias()){

            Cell from = getCellFromName(rawConnection.getOrigin());
            Cell to = getCellFromName(rawConnection.getDestination());

            Connection auxConnection = new Connection(rawConnection, from, to);
            from.connections.add(auxConnection);


        }


    }

    private Cell getCellFromName(String name){

        for(Cell currentCell : allCells){
            if (currentCell.city.getName().equals(name)){
                return currentCell;
            }
        }

        return null;

    }


    private Cell getSmallestFromOpen(){

        long currentF = Long.MAX_VALUE;
        Cell smallestCell = null;

        for(Cell aux : open){

            if (aux.getF() < currentF){
                smallestCell = aux;
            }

        }

        return smallestCell;
    }


    public void startAStar(){

        System.out.println("Adding start node to open");
        //Add first node
        open.add(start);

        while(true){

            Cell currentNode = getSmallestFromOpen();

            System.out.println("\nGrabbed smallest node: " + currentNode.city.getName());

            //pop from open
            open.remove(currentNode);
            //add to closed
            closed.add(currentNode);

            if (currentNode == end){
                //TODO:Final node found
                System.out.println("FOUND!");
                routeCalculated = true;
                return;
            }

            System.out.println("\tEvaluating successors:");

            //evaluate successors
            for (Connection connection : currentNode.connections){

                System.out.println("\t\tConnection: " +  connection.from.city.getName() + " -> " + connection.to.city.getName());

                if (connection.to == end){
                    //TODO:Final node found
                    System.out.println("FOUND");
                    connection.to.parent = connection.from;
                    routeCalculated = true;
                    return;
                }

                System.out.println("\t\t\tEvaluating current connection");
                //Evaluate current connection
                connection.to.evaluate(connection.from, connection);

                //If successor node is valid and is not in either lists, add to open.
                if (!isCellAdded(connection.to)){
                    open.add(connection.to);
                }


            }

        }

    }

    private boolean isCellAdded(Cell node){

        for (Cell aux : open){
            if (aux == node){
                return true;
            }
        }

        for (Cell aux : closed){
            if (aux == node){
                return true;
            }
        }

        return false;

    }

    private void traceBackRoute(){

        boolean arrivedStart = false;
        Cell auxCell = end;

        ArrayList<Cell> route = new ArrayList<>();

        while(!arrivedStart){

            if (auxCell.isStartCell){
                route.add(auxCell);
                arrivedStart = true;
            }else{
                route.add(auxCell);
                auxCell = auxCell.parent;

            }

        }

        System.out.println("ROUTE:");

        //print the root in inverse order
        for (int i = route.size() - 1; i >= 0; i--){

            if (i == 0){

                System.out.print(route.get(i).city.getName());

            }else{
                System.out.print(route.get(i).city.getName() + " -> ");
            }


        }

    }

    public void showRoute(){

        if (routeCalculated){

            traceBackRoute();

        }else {

            System.out.println("Route hasn't been calculated yet");

        }

    }

}
