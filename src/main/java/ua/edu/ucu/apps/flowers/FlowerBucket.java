package ua.edu.ucu.apps.flowers;

import java.util.ArrayList;
import java.util.List;

public class FlowerBucket {
    private List<FlowerPack> flowerPacks;

    public FlowerBucket() {
        flowerPacks = new ArrayList<>();
    }

    public void add(FlowerPack flowerPack) {
        flowerPacks.add(flowerPack);
    }

    public double getPrice() {
        double price = 0;
        for (FlowerPack flowerPack: flowerPacks) {
            price += flowerPack.getPrice();
        }
        return price;
    }

    public boolean search(FlowerSpec flowerSpec){
        for (FlowerPack flowerPack : flowerPacks) {
            if (flowerPack.matches(flowerSpec)){
                return true;
            }
        }
        return false;
    }
}

// Закон Деметри не використовувати дві "." (a.m().n() bad)
// Прочитати Head First OOP design and Analysis