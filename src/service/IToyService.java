package service;

import data.ToyStore;
import java.io.IOException;


public interface IToyService <T extends ToyStore>{
    T assortmentReplenishment(String pathName, String pathNameToPrizeList) throws IOException, NumberFormatException;
    void weightChange(String pathName) throws IOException;
    void showAllAssortment(String pathName) throws IOException;
    void addToPrizeList(String pathName, String pathNameToPrizeList) throws IOException;
    void giveOutAPrize(String pathNameToPrizeList) throws IOException;
}
