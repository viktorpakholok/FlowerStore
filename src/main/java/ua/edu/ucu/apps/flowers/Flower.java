package ua.edu.ucu.apps.flowers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Flower extends FlowerSpec {
    private double price;

    public Flower() { 

    }

    public Flower(double price, FlowerColor color, int sepalLength, FlowerType flowerType) {
        super(color, sepalLength, flowerType);
        this.price = price;
    }

    // copy constructor
    public Flower(Flower flower) {
        price = flower.price;
        setColor(FlowerColor.getColor(flower.getColor()));
        setSepalLength(flower.getSepalLength());
        setFlowerType(flower.getFlowerType());
    }

    public boolean matches(FlowerSpec flowerSpec){
        return super.matches(flowerSpec);
    }
}
