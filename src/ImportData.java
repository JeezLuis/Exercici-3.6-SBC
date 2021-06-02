import Model.City;
import Model.Distancia;
import Model.Graf;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ImportData {

    private Graf graf;

    public ImportData(String path) {

        Gson gson = new Gson();
        JsonObject allData = null;

        JsonArray cities_import = null;
        JsonArray routes_import = null;

        try {

            allData = new JsonParser().parse(new FileReader(path)).getAsJsonObject();

            cities_import = allData.getAsJsonArray("cities");
            City[] cities = new City[cities_import.size()];
            for (int i = 0; i < cities_import.size(); i++){
                City aux = new City(cities_import.get(i).getAsJsonObject().get("name").getAsString(),
                                    cities_import.get(i).getAsJsonObject().get("address").getAsString(),
                                    cities_import.get(i).getAsJsonObject().get("country").getAsString(),
                                    cities_import.get(i).getAsJsonObject().get("latitude").getAsLong(),
                                    cities_import.get(i).getAsJsonObject().get("longitude").getAsLong());
                cities[i] = aux;
            }

            routes_import = allData.getAsJsonArray("connections");
            Distancia[] distancias = new Distancia[routes_import.size()];
            for (int i = 0; i < routes_import.size(); i++){
                Distancia aux = new Distancia(  routes_import.get(i).getAsJsonObject().get("from").getAsString(),
                                                routes_import.get(i).getAsJsonObject().get("to").getAsString(),
                                                routes_import.get(i).getAsJsonObject().get("distance").getAsInt(),
                                                routes_import.get(i).getAsJsonObject().get("duration").getAsInt());
                distancias[i] = aux;
             }

            this.graf = new Graf(cities, distancias);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Graf getGraf() {
        return graf;
    }
}
