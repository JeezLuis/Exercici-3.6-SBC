import Model.City;
import Model.Tree;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ImportData {

    private Tree tree;

    public ImportData(String path) {
        Gson gson = new Gson();
        JsonObject allData = null;

        JsonArray cities_import = null;
        JsonArray routes_import = null;

        ArrayList<City> cities = new ArrayList<>();

        try {

            allData = new JsonParser().parse(new FileReader(path)).getAsJsonObject();

            cities_import = allData.getAsJsonArray("cities");
            for (int i = 0; i < cities_import.size(); i++){
                cities.add(gson.fromJson(cities_import.get(i).getAsJsonObject(), City.class));
            }

            routes_import = allData.getAsJsonArray("cities");
            for (int i = 0; i < routes_import.size(); i++){
                tree.insertConnection(  routes_import.get(i).getAsJsonObject().get("from").getAsString(),
                                        routes_import.get(i).getAsJsonObject().get("to").getAsString(),
                                        routes_import.get(i).getAsJsonObject().get("distance").getAsInt(),
                                        routes_import.get(i).getAsJsonObject().get("duration").getAsInt(),
                                        cities);
            }


            } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Test");
    }
}
