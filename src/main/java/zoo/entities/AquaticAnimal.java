package zoo.entities;

import zoo.entities.animals.Animal;

public class AquaticAnimal extends BaseAnimal {
    private final static double INITIAL_KG = 2.50;
    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KG, price);
    }

    @Override
    public void eat() {
       super.setKg(this.getKg() + 7.50);
    }
}
