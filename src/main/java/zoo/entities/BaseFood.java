package zoo.entities;

import zoo.entities.foods.Food;

public abstract class BaseFood implements Food {
    private int calories;
    private double price;

    public BaseFood(int calories, double price) {
        this.calories = calories;
        this.price = price;
    }

    @Override
    public int getCalories() {
        return this.calories;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    protected void setCalories(int calories) {
        this.calories = calories;
    }

    protected void setPrice(double price) {
        this.price = price;
    }
}
