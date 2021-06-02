package AEstrellaGraph;

import Model.Distancia;

public class Connection {

    protected Distancia rawConnection;
    protected long length;
    protected Cell from;
    protected Cell to;

    public Connection(Distancia rawConnection, Cell from, Cell to){

        this.from = from;
        this.to = to;
        this.rawConnection = rawConnection;

        length = rawConnection.getDistance();

    }

}
