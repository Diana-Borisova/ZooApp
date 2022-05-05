package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.LandArea;
import zoo.entities.Meat;
import zoo.entities.Vegetable;
import zoo.entities.WaterArea;
import zoo.entities.areas.Area;
import zoo.entities.foods.Food;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

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
            throw new NullPointerException(String.format(ExceptionMessages.INVALID_AREA_TYPE));
        }
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
       if (this.foodRepository.findByType(foodType) != null &&
               (areaName.equals("WaterArea") || areaName.equals("LandArea"))){
           Food food = this.foodRepository.findByType(foodType);
           this.foodRepository.remove(food);
       }
        return null;
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        return null;
    }

    @Override
    public String feedAnimal(String areaName) {
        return null;
    }

    @Override
    public String calculateKg(String areaName) {
        return null;
    }

    @Override
    public String getStatistics() {
        return null;
    }
}
