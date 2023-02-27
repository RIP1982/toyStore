package util;

import data.Assortment;
import data.ToyStore;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFromCsv {

    public static ToyStore readFromCsv(String pathName) throws IOException {
        try {
            ArrayList<Assortment> toysList = new ArrayList<>();
            BufferedReader csvReader = new BufferedReader(new FileReader(pathName));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                int id = Integer.parseInt(data[0].replace("\"", ""));
                String name = data[1].replace("\"", "");
                Integer weight = Integer.valueOf(data[2].replace("\"", ""));
                Integer quantity = Integer.valueOf(data[3].replace("\"", ""));
                Assortment toy = new Assortment(id, name, weight, quantity);
                toysList.add(toy);
            }
            csvReader.close();
            ToyStore toyStore = new ToyStore(toysList);
            return toyStore;
        } catch (Exception e) {
            return null;
        }
    }
}
