package ua.edu.ucu.apps.flowers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Flower {
    private FlowerColor color;
    private int sepalLength;
    private double price;
    private FlowerType flowerType;


    // copy constructor
    public Flower(Flower flower) {
        color = flower.color;
        sepalLength = flower.sepalLength;
        price = flower.price;
        flowerType = flower.flowerType;
    }

    public String getColor() {
        return color.toString();
    }
}
