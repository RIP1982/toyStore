package util;

import data.Assortment;
import com.opencsv.CSVWriter;
import data.ToyStore;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToCsv {
    public static void writeToCsv(ToyStore toyStore,String name) {
        ArrayList<Assortment> toysList = toyStore.getToysList();
        List<String[]> assortmentToString = new ArrayList<>();
        for (Assortment toy: toysList) {
            String[] toyDetailsToString = new String[]{String.valueOf(toy.getId()), toy.getName(),
                    String.valueOf(toy.getWeight()), String.valueOf(toy.getQuantity())};
            assortmentToString.add(toyDetailsToString);
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(name))) {
            writer.writeAll(assortmentToString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
