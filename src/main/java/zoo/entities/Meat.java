package zoo.entities;

public class Meat extends BaseFood{
    private final static int INITIAL_CALORIES = 70;
    private final static double INITIAL_PRICE= 10;

    public Meat() {
        super(INITIAL_CALORIES, INITIAL_PRICE);
    }
}
