package data;

import java.util.ArrayList;

public class ToyStore{
    private ArrayList<Assortment> toysList;

    public ToyStore(ArrayList<Assortment> toysList) {
        this.toysList = toysList;
    }

    public ArrayList<Assortment> getToysList() {
        return toysList;
    }

    public void setToysList(ArrayList<Assortment> toysList) {
        this.toysList = toysList;
    }

    @Override
    public String toString() {
        return "ToyStore assortment:" + toysList;
    }
}
