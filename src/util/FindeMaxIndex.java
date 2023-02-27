package util;

import data.Assortment;
import java.util.ArrayList;

public class FindeMaxIndex {
    public static int findeMaxIndex(ArrayList<Assortment> list) {
        int maxIndex = 0;
        for (Assortment toy: list) {
            if (maxIndex < toy.getId()) {
                maxIndex = toy.getId();
            }
        }
        return maxIndex;
    }
}
