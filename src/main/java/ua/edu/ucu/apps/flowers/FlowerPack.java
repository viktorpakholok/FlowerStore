package ua.edu.ucu.apps.flowers;


public class FlowerPack {
    private int quantity;
    private Flower flower;

    public FlowerPack(Flower flower, int quantity) {
        this.flower = new Flower(flower);
        this.quantity = quantity;
    }

    public double getPrice() {
        return flower.getPrice() * quantity;
    }

    public boolean matches(FlowerSpec flowerSpec){
        if (flower.matches(flowerSpec)) {
            return true;
        }
        return false;
    }
}
