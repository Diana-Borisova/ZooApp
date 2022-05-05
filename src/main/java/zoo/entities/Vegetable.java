package zoo.entities;

public class Vegetable extends BaseFood{
    private final static int INITIAL_CALORIES = 50;
    private final static double INITIAL_PRICE= 5;


    public Vegetable() {
        super(INITIAL_CALORIES, INITIAL_PRICE);
    }
}

