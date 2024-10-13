package flowers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.edu.ucu.apps.flowers.Flower;
import ua.edu.ucu.apps.flowers.FlowerColor;
import ua.edu.ucu.apps.flowers.FlowerSpec;

import java.util.Random;
import org.junit.jupiter.api.Assertions;

public class FlowerTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_PRICE = 100;
    private static final int TEST_PRICE = 50;
    private static final int TEST_SEPAL_LENGTH = 15;
    private static final int DEFAULT_SEPAL_LENGTH = 10;
    private static final String GREEN_RGB = "#00FF00";
    private static final String INVALID_RGB = "#123456";

    private Flower flower;

    @BeforeEach
    public void init() {
        flower = new Flower();
    }

    @Test
    public void testPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
        flower.setPrice(price);
        Assertions.assertEquals(
            price, flower.getPrice(), 
            "Price did not match"
        );
    }

    @Test
    public void testColor() {
        FlowerColor color = FlowerColor.RED;
        flower.setColor(color);
        Assertions.assertEquals(
            "#FF0000", flower.getColor(), 
            "Color did not match"
        );
    }

    @Test
    public void testSepalLength() {
        flower.setSepalLength(TEST_SEPAL_LENGTH);
        Assertions.assertEquals(
            TEST_SEPAL_LENGTH, flower.getSepalLength(), 
            "Sepal length did not match"
        );
    }

    @Test
    public void testCopyConstructor() {
        flower.setPrice(TEST_PRICE);
        flower.setColor(FlowerColor.BLUE);
        flower.setSepalLength(DEFAULT_SEPAL_LENGTH);

        Flower copiedFlower = new Flower(flower);
        Assertions.assertEquals(
            flower.getPrice(), copiedFlower.getPrice(), 
            "Price mismatch in copy constructor"
        );
        Assertions.assertEquals(
            flower.getColor(), copiedFlower.getColor(), 
            "Color mismatch in copy constructor"
        );
        Assertions.assertEquals(
            flower.getSepalLength(), copiedFlower.getSepalLength(), 
            "Sepal length mismatch in copy constructor"
        );
    }

    @Test
    public void testMatches() {
        FlowerSpec spec = new FlowerSpec(
            FlowerColor.RED, DEFAULT_SEPAL_LENGTH, null);
        flower.setColor(FlowerColor.RED);
        flower.setSepalLength(DEFAULT_SEPAL_LENGTH);

        Assertions.assertTrue(
            flower.matches(spec), 
            "Flower should match the spec"
        );
    }

    @Test
    public void testDoesNotMatch() {
        FlowerSpec spec = new FlowerSpec(
            FlowerColor.RED, TEST_SEPAL_LENGTH, null);
        flower.setColor(FlowerColor.BLUE);
        flower.setSepalLength(DEFAULT_SEPAL_LENGTH);

        Assertions.assertFalse(
            flower.matches(spec), 
            "Flower should not match the spec"
        );
    }

    @Test
    public void testGetColorFromRGB() {
        FlowerColor color = FlowerColor.getColor(GREEN_RGB);
        Assertions.assertEquals(
            FlowerColor.GREEN, color, 
            "Color mismatch for RGB #00FF00"
        );
    }

    @Test
    public void testGetColorFromRGBInvalid() {
        Assertions.assertThrows(
            IllegalArgumentException.class, 
            () -> FlowerColor.getColor(INVALID_RGB), 
            "Expected exception for unknown RGB code"
        );
    }

    @Test
    public void testInvalidRGBFormat() {
        Assertions.assertThrows(
            IllegalArgumentException.class, 
            () -> FlowerColor.getColor("00FF00"), 
            "Expected exception for missing '#' in RGB code"
        );
    }
}
