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

    private static final int TEST_PRICE_ONE = 10;
    private static final int TEST_QUANTITY_ONE = 5;
    private static final int TEST_PRICE_TWO = 20;
    private static final int TEST_QUANTITY_TWO = 3;
    private static final int NEGATIVE_QUANTITY = -5;
    private static final int TEST_QUANTITY = 10;
    private static final int DEFAULT_SEPAL_LENGTH = 10;

    private FlowerBucket flowerBucket;

    @BeforeEach
    public void init() {
        flowerBucket = new FlowerBucket();
    }

    @Test
    public void testSingleFlowerPackPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE) + 1;
        int quantity = RANDOM_GENERATOR.nextInt(MAX_QUANTITY) + 1;

        Flower flower = new Flower(
            price, FlowerColor.RED, DEFAULT_SEPAL_LENGTH, FlowerType.ROSE
        );
        FlowerPack flowerPack = new FlowerPack(flower, quantity);
        flowerBucket.add(flowerPack);

        Assertions.assertEquals(
            price * quantity, flowerBucket.getPrice(),
            "Incorrect total price for a single flower pack"
        );
    }

    @Test
    public void testMultipleFlowerPacksPrice() {
        Flower flower1 = new Flower(
            TEST_PRICE_ONE, FlowerColor.GREEN, DEFAULT_SEPAL_LENGTH, FlowerType.CHAMOMILE
        );
        FlowerPack flowerPack1 = new FlowerPack(flower1, TEST_QUANTITY_ONE);

        Flower flower2 = new Flower(
            TEST_PRICE_TWO, FlowerColor.BLUE, DEFAULT_SEPAL_LENGTH, FlowerType.TULIP
        );
        FlowerPack flowerPack2 = new FlowerPack(flower2, TEST_QUANTITY_TWO);

        flowerBucket.add(flowerPack1);
        flowerBucket.add(flowerPack2);

        int expectedPrice = (TEST_PRICE_ONE * TEST_QUANTITY_ONE) + (TEST_PRICE_TWO * TEST_QUANTITY_TWO);
        Assertions.assertEquals(
            expectedPrice, flowerBucket.getPrice(),
            "Incorrect total price for multiple flower packs"
        );
    }

    @Test
    public void testEmptyBucketPrice() {
        Assertions.assertEquals(
            0, flowerBucket.getPrice(),
            "Price of an empty flower bucket should be 0"
        );
    }

    @Test
    public void testZeroQuantityFlowerPack() {
        Flower flower = new Flower(
            50, FlowerColor.YELLOW, 2, FlowerType.ROSE
        );
        FlowerPack flowerPack = new FlowerPack(flower, 0);
        flowerBucket.add(flowerPack);

        Assertions.assertEquals(
            0, flowerBucket.getPrice(),
            "Price should be 0 when flower pack quantity is 0"
        );
    }

    @Test
    public void testNegativeQuantityThrowsException() {
        Flower flower = new Flower(
            50, FlowerColor.WHITE, 9, FlowerType.ROSE
        );

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new FlowerPack(flower, NEGATIVE_QUANTITY),
            "Expected IllegalArgumentException for negative quantity"
        );
    }

    @Test
    public void testNullFlowerThrowsException() {
        Assertions.assertThrows(
            NullPointerException.class,
            () -> flowerBucket.add(new FlowerPack(null, TEST_QUANTITY)),
            "Expected NullPointerException for null flower in pack"
        );
    }
}
