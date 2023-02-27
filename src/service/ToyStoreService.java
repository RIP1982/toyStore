package service;

import data.Assortment;
import data.ToyStore;
import util.FindeMaxIndex;
import util.ReadFromConsole;
import util.ReadFromCsv;
import util.WriteToCsv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class ToyStoreService implements IToyService {

    Logger logger = Logger.getAnonymousLogger();
    @Override
    public ToyStore assortmentReplenishment(String pathName, String pathNameToPrizeList) throws IOException,
            NullPointerException {
        ToyStore toyStoreOld = ReadFromCsv.readFromCsv(pathName);
        ToyStore prizeAssortment = ReadFromCsv.readFromCsv(pathNameToPrizeList);
        ArrayList<Assortment> toysList = new ArrayList<>();
        try {
            Assortment toy = ReadFromConsole.readFromConsole();
            if (toyStoreOld != null) {
                toysList = toyStoreOld.getToysList();
                for (Assortment thisToy: toysList) {
                    if (toy.getName().equals(thisToy.getName())) {
                        thisToy.setWeight(toy.getWeight());
                        thisToy.setQuantity(thisToy.getQuantity() + toy.getQuantity());
                        ToyStore toyStore = new ToyStore(toysList);
                        WriteToCsv.writeToCsv(toyStore, pathName);
                        return toyStore;
                    }
                }
                int maxIdToysList = FindeMaxIndex.findeMaxIndex(toysList);
                if (prizeAssortment == null) {

                    toy.setId(maxIdToysList + 1);
                    toysList.add(toy);
                    ToyStore toyStore = new ToyStore(toysList);
                    WriteToCsv.writeToCsv(toyStore, pathName);
                    return toyStore;
                } else {
                    boolean flag = false;
                    ArrayList<Assortment> prizeList = prizeAssortment.getToysList();
                    int maxIdPrizeList = FindeMaxIndex.findeMaxIndex(prizeList);
                    for (Assortment prize: prizeList) {
                        if (toy.getName().equals(prize.getName())) {
                            flag = true;
                            toy.setId(prize.getId());
                            toysList.add(toy);
                            ToyStore toyStore = new ToyStore(toysList);
                            WriteToCsv.writeToCsv(toyStore, pathName);
                            return toyStore;
                        }
                    }
                    if (flag == false) {
                        if (maxIdPrizeList > maxIdToysList) {
                            toy.setId(maxIdPrizeList + 1);
                        } else { toy.setId(maxIdToysList + 1); }
                        toysList.add(toy);
                        ToyStore toyStore = new ToyStore(toysList);
                        WriteToCsv.writeToCsv(toyStore, pathName);
                        return toyStore;
                    }
                }
            } else {
                toy.setId(1);
                toysList.add(toy);
                ToyStore toyStore = new ToyStore(toysList);
                WriteToCsv.writeToCsv(toyStore, pathName);
                return toyStore;
            }
        } catch (Exception e) {
            System.out.println();
        }
        return null;
    }

    @Override
    public void weightChange(String pathName) throws IOException{
        ToyStore toyStoreOld = ReadFromCsv.readFromCsv(pathName);
        ArrayList<Assortment> toysList = toyStoreOld.getToysList();
        System.out.printf("Enter id: ");
        Long id = Long.valueOf(new Scanner(System.in).nextLine());
        for (Assortment toy: toysList) {
            if (toy.getId() == id) {
                System.out.printf("Enter a new weight%: ");
                toy.setWeight(Integer.valueOf(new Scanner(System.in).nextLine()));
            }
        }
        ToyStore toyStore = new ToyStore(toysList);
        WriteToCsv.writeToCsv(toyStore, pathName);
    }

    @Override
    public void showAllAssortment(String pathName) throws  IOException{
        ToyStore toyStore = ReadFromCsv.readFromCsv(pathName);
        logger.info(toyStore.toString());
    }

    @Override
    public void addToPrizeList(String pathName, String pathNameToPrizeList) throws IOException{
        ToyStore toyStoreOld = ReadFromCsv.readFromCsv(pathName);
        ArrayList<Assortment> toysList = toyStoreOld.getToysList();
        try {
            int maxWeight = 0;
            int maxIndex = 0;
            for (int i = 0; i < toysList.size(); i++) {
                if (maxWeight < toysList.get(i).getWeight()) {
                    maxWeight = toysList.get(i).getWeight();
                    maxIndex = i;
                }
            }
            Assortment toyMaxIndex = toysList.get(maxIndex);
            logger.info("Prize: " + toyMaxIndex.getName());
            ToyStore prizeAssortment = ReadFromCsv.readFromCsv(pathNameToPrizeList);
            ArrayList<Assortment> prizeList = new ArrayList<>();
            if (prizeAssortment != null) {
                boolean flag = false;
                prizeList = prizeAssortment.getToysList();
                for (Assortment toy: prizeList) {
                    if (toy.getId() == toyMaxIndex.getId()) {
                        flag = true;
                        toy.setQuantity(toy.getQuantity() + 1);
                    }
                }
                if (flag == false) {
                    toyMaxIndex.setQuantity(1);
                    prizeList.add(toyMaxIndex);
                }
            } else {
                toyMaxIndex.setQuantity(1);
                prizeList.add(toyMaxIndex);
            }
            ToyStore toyStore = new ToyStore(prizeList);
            WriteToCsv.writeToCsv(toyStore, pathNameToPrizeList);
            if (toysList.get(maxIndex).getQuantity() > 1) {
                toysList.get(maxIndex).setQuantity(toysList.get(maxIndex).getQuantity() - 1);
                ToyStore toyStoreNew = new ToyStore(toysList);
                WriteToCsv.writeToCsv(toyStoreNew, pathName);
            } else {
                toysList.remove(maxIndex);
                ToyStore toyStoreNew = new ToyStore(toysList);
                WriteToCsv.writeToCsv(toyStoreNew, pathName);
            }
        } catch (Exception e){ logger.info("Toys not enough!"); }
    }

    @Override
    public void giveOutAPrize(String pathNameToPrizeList) throws IOException{
        ToyStore prizeAssortment = ReadFromCsv.readFromCsv(pathNameToPrizeList);
        ArrayList<Assortment> prizeList = prizeAssortment.getToysList();
        try {
            int maxWeight = 0;
            int maxIndex = 0;
            for (int i = 0; i < prizeList.size(); i++) {
                if (maxWeight < prizeList.get(i).getWeight()) {
                    maxWeight = prizeList.get(i).getWeight();
                    maxIndex = i;
                }
            }
            Assortment toyMaxIndex = prizeList.get(maxIndex);
            logger.info("Give out a prize: " + toyMaxIndex.getName());
            if (prizeList.get(maxIndex).getQuantity() > 1) {
                prizeList.get(maxIndex).setQuantity(prizeList.get(maxIndex).getQuantity() - 1);
                ToyStore prizeAssortmentNew = new ToyStore(prizeList);
                WriteToCsv.writeToCsv(prizeAssortmentNew, pathNameToPrizeList);
            } else {
                prizeList.remove(maxIndex);
                ToyStore prizeAssortmentNew = new ToyStore(prizeList);
                WriteToCsv.writeToCsv(prizeAssortmentNew, pathNameToPrizeList);
            }
        } catch (Exception e){ logger.info("Toys not enough!"); }
    }
}
