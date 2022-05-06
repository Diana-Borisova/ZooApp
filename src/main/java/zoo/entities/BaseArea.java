package zoo.entities;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.areas.Area;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseArea implements Area {
    private String  name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        return this.foods.stream().mapToInt(Food::getCalories).sum();
    }
    @Override
    public void addAnimal(Animal animal) {
        if (this.capacity<this.animals.size()){
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        for (Animal animal :this.animals) animal.eat();
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        if (this.animals.isEmpty()){
            sb.append("none").append(System.lineSeparator());
        } else {
            sb.append(String.format("%s (%s):",  this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
            sb.append("Animals: ").append(this.animals.stream().map(Animal::getName).collect(Collectors.joining(" "))).append(System.lineSeparator());
            sb.append(String.format("Foods: %d", this.foods.size())).append(System.lineSeparator());
            sb.append(String.format("Calories: %d", this.foods.stream().mapToInt(Food::getCalories).sum())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
