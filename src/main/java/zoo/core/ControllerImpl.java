package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.*;
import zoo.entities.animals.Animal;
import zoo.entities.areas.Area;
import zoo.entities.foods.Food;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private Collection<Area>areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area = null;
        if (areaType.equals("WaterArea")) {
            area = new WaterArea(areaName);
        } else if (areaType.equals("LandArea")){
            area = new LandArea(areaName);

        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }
        this.areas.add(area);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }
    @Override
    public String buyFood(String foodType) {
        Food food = null;
        if (foodType.equals("Vegetable")){
            food = new Vegetable();
        } else if (foodType.equals("Meat")){
    food= new Meat();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }
        this.foodRepository.add(food);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        if (this.foodRepository.findByType(foodType) == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));
        }
       if (this.foodRepository.findByType(foodType) != null) {
           Food food = this.foodRepository.findByType(foodType);
           for (Area area :this.areas) {
               if (area.getName().equals(areaName)){
                   area.addFood(food);
                 //  this.areas.add(area);
               }
           }
          //Area area1 = this.areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);
          // area1.addFood(food);

           this.foodRepository.remove(food);

       }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal = null;
        if (!animalType.equals("AquaticAnimal") && !animalType.equals("TerrestrialAnimal")){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }
        if (animalType.equals("AquaticAnimal")){
          Area currentArea =  this.areas.stream().filter(area -> area.getClass().getSimpleName().equals("WaterArea")).findFirst().orElse(null);
        if (currentArea == null){
            throw new NullPointerException(ConstantMessages.AREA_NOT_SUITABLE);
        } else {
            animal = new AquaticAnimal(animalName,kind,price);
            currentArea.addAnimal(animal);

        }
        }else {
            Area currentArea = this.areas.stream().filter(area -> area.getClass().getSimpleName().equals("LandArea")).findFirst().orElse(null);
            if (currentArea == null) {
                throw new NullPointerException(ConstantMessages.AREA_NOT_SUITABLE);
            }else {
                animal = new TerrestrialAnimal(animalName,kind,price);
                currentArea.addAnimal(animal);
            }
        }


          return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType,areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        int count = 0;
        for (Area area :this.areas) {
            if (areaName.equals(area.getName())){
                for (Animal animal :area.getAnimals()) {
                    animal.eat();
                    count++;
                }
            }
        }
        return String.format(ConstantMessages.ANIMALS_FED, count);
    }

    @Override
    public String calculateKg(String areaName) {
        double allAnimalsKg = 0.0;
        for (Area area :this.areas) {
            if (areaName.equals(area.getName())){

                for (Animal animal :area.getAnimals()) {
                   allAnimalsKg += animal.getKg();

                }
            }
        }
        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName,allAnimalsKg);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Area area :this.areas) {
            sb.append(area.getInfo()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
