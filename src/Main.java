import Model.City;

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

                new AEstrella(id.getGraf(), from, to);

                new CSP(id.getGraf(), from, to);

            }

        }

    }

}
