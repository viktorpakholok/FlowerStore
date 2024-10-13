package ua.edu.ucu.apps.flowers;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlowerSpec {
    private FlowerColor color;
    private int sepalLength;
    private FlowerType flowerType;

    public FlowerSpec(){}

    public FlowerSpec(FlowerColor color, int sepalLength, FlowerType flowerType){
        this.color = color;
        this.sepalLength = sepalLength;
        this.flowerType = flowerType;
    }

    public FlowerSpec(FlowerSpec flowerSpec){
        color = flowerSpec.color;
        sepalLength = flowerSpec.sepalLength;
        flowerType = flowerSpec.flowerType;
    }

    public String getColor() {
        return color.toString();
    }

    public boolean matches(FlowerSpec flowerSpec){
        System.out.println(this.color + ", " + this.sepalLength + ", " + this.flowerType);
        System.out.println(flowerSpec.color + ", " + flowerSpec.sepalLength + ", " + flowerSpec.flowerType);
        if (color == flowerSpec.color && 
            sepalLength == flowerSpec.sepalLength && 
            flowerType == flowerSpec.flowerType) {
            return true;
        }
        return false;
    }
}
