package zoo.entities;

import zoo.entities.BaseArea;

public class LandArea extends BaseArea {
    private final static int INITIAL_CAPACITY = 25;
    public LandArea(String name) {
        super(name,INITIAL_CAPACITY);
    }
}
