package flowers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Random;
import ua.edu.ucu.apps.flowers.FlowerBucket;
import ua.edu.ucu.apps.flowers.FlowerColor;
import ua.edu.ucu.apps.flowers.FlowerPack;
import ua.edu.ucu.apps.flowers.FlowerType;
import ua.edu.ucu.apps.flowers.Flower;

public class FlowerBucketTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_QUANTITY = 1000;
    private static final int MAX_PRICE = 100;

    private FlowerBucket flowerBucket;

    @BeforeEach
    public void init() {
        flowerBucket = new FlowerBucket();
    }

    @Test
    public void testSingleFlowerPackPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE) + 1;  // Ensure non-zero price
        int quantity = RANDOM_GENERATOR.nextInt(MAX_QUANTITY) + 1;  // Ensure non-zero quantity
        Flower flower = new Flower(price, FlowerColor.RED, 10, FlowerType.ROSE);
        flower.setPrice(price);
        FlowerPack flowerPack = new FlowerPack(flower, quantity);
        flowerBucket.add(flowerPack);
        Assertions.assertEquals(price * quantity, flowerBucket.getPrice(),
                "Incorrect total price for a single flower pack");
    }

    @Test
    public void testMultipleFlowerPacksPrice() {
        int price1 = 10;
        int quantity1 = 5;
        Flower flower1 = new Flower(price1, FlowerColor.GREEN, 10, FlowerType.CHAMOMILE);
        FlowerPack flowerPack1 = new FlowerPack(flower1, quantity1);

        int price2 = 20;
        int quantity2 = 3;
        Flower flower2 = new Flower(price2, FlowerColor.BLUE, 5, FlowerType.TULIP);
        FlowerPack flowerPack2 = new FlowerPack(flower2, quantity2);

        flowerBucket.add(flowerPack1);
        flowerBucket.add(flowerPack2);

        int expectedPrice = (price1 * quantity1) + (price2 * quantity2);
        Assertions.assertEquals(expectedPrice, flowerBucket.getPrice(),
                "Incorrect total price for multiple flower packs");
    }

    @Test
    public void testEmptyBucketPrice() {
        Assertions.assertEquals(0, flowerBucket.getPrice(),
                "Price of an empty flower bucket should be 0");
    }

    @Test
    public void testZeroQuantityFlowerPack() {
        Flower flower = new Flower(0, FlowerColor.YELLOW, 2, FlowerType.ROSE);
        flower.setPrice(50);
        FlowerPack flowerPack = new FlowerPack(flower, 0);
        flowerBucket.add(flowerPack);

        Assertions.assertEquals(0, flowerBucket.getPrice(),
                "Price should be 0 when flower pack quantity is 0");
    }

    @Test
    public void testNegativeQuantityThrowsException() {
        Flower flower = new Flower(0, FlowerColor.WHITE, 9, FlowerType.ROSE);
        flower.setPrice(50);
        Assertions.assertThrows(IllegalArgumentException.class, 
            () -> new FlowerPack(flower, -5), 
            "Expected IllegalArgumentException for negative quantity");
    }

    @Test
    public void testNullFlowerThrowsException() {
        Assertions.assertThrows(NullPointerException.class, 
            () -> flowerBucket.add(new FlowerPack(null, 10)), 
            "Expected NullPointerException for null flower in pack");
    }
}
