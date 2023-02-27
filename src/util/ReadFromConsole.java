package util;

import data.Assortment;
import java.util.Scanner;
import java.util.logging.Logger;


public class ReadFromConsole {
    public static Assortment readFromConsole() throws NumberFormatException {
        try {
            int id = 0;
            System.out.printf("name: ");
            String name = (new Scanner(System.in).nextLine());
            System.out.printf("weight: ");
            Integer weight = Integer.valueOf(new Scanner(System.in).nextLine());
            System.out.printf("quantity: ");
            Integer quantity = Integer.valueOf(new Scanner(System.in).nextLine());
            Assortment assortment = new Assortment(id, name, weight, quantity);
            return assortment;
        } catch (Exception e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.info("You entered wrong data!");
        }
        return null;
    }
}
