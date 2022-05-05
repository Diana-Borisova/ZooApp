package zoo.entities;

public class TerrestrialAnimal extends BaseAnimal {
    private final static double INITIAL_KG = 5.50;
    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KG, price);
    }

    @Override
    public void eat() {

        super.setKg(this.getKg() + 5.70);
    }
}
