package data;

public class Assortment extends Toy{
    private int id;
    private int quantity;

    public Assortment(int id, String name, int weight, int quantity) {
        super(name, weight);
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\n" + "{ " + "id = " + getId() + "; name = " +
                getName() + "; weight = " + getWeight() +
                "; quantity = " + quantity +
                "}";
    }
}
