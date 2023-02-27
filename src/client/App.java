package client;

import data.Assortment;
import service.IToyService;
import service.ToyStoreService;
import view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class App {
    View ui;
    IToyService toyService = new ToyStoreService();
    ArrayList<Assortment> toysList = new ArrayList<>();
    Logger logger = Logger.getAnonymousLogger();

    public App() {
        ui = new ConsoleView();
    }

    public void start() throws IOException {
        StringBuilder sb = new StringBuilder()
                .append("\n ==== \n")
                .append("1 - assortment replenishment\n")
                .append("2 - weight change\n")
                .append("3 - show all assortment\n")
                .append("4 - add to prize list\n")
                .append("5 - show list of prizes \n")
                .append("6 - give out a prize \n")
                .append("0 - exit\n");

        while (true) {
            ui.set(sb.toString());
            System.out.printf("Enter your request, please: ");
            switch (ui.get()) {
                case "1":
                    toyService.assortmentReplenishment("src/toyStoreAssortment.csv",
                            "src/prizeList.csv");
                    break;
                case "2":
                    toyService.weightChange("src/toyStoreAssortment.csv");
                    break;
                case "3":
                    toyService.showAllAssortment("src/toyStoreAssortment.csv");
                    break;
                case "4":
                    toyService.addToPrizeList("src/toyStoreAssortment.csv",
                            "src/prizeList.csv");
                    break;
                case "5":
                    toyService.showAllAssortment("src/prizeList.csv");
                    break;
                case "6":
                    toyService.giveOutAPrize("src/prizeList.csv");
                    break;
                case "0":
                    return;
            }
        }
    }
}
