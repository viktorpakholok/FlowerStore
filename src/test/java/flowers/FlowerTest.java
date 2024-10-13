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
    private Flower flower;

    @BeforeEach
    public void init() {
        flower = new Flower();
    }

    @Test
    public void testPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
        flower.setPrice(price);
        Assertions.assertEquals(price, flower.getPrice(), "Price did not match");
    }

    @Test
    public void testColor() {
        FlowerColor color = FlowerColor.RED;
        flower.setColor(color);
        Assertions.assertEquals("#FF0000", flower.getColor(), "Color did not match");
    }

    @Test
    public void testSepalLength() {
        int sepalLength = 15;
        flower.setSepalLength(sepalLength);
        Assertions.assertEquals(sepalLength, flower.getSepalLength(), "Sepal length did not match");
    }

    @Test
    public void testCopyConstructor() {
        flower.setPrice(50);
        flower.setColor(FlowerColor.BLUE);
        flower.setSepalLength(10);

        Flower copiedFlower = new Flower(flower);
        Assertions.assertEquals(flower.getPrice(), copiedFlower.getPrice(), "Price mismatch in copy constructor");
        Assertions.assertEquals(flower.getColor(), copiedFlower.getColor(), "Color mismatch in copy constructor");
        Assertions.assertEquals(flower.getSepalLength(), copiedFlower.getSepalLength(), "Sepal length mismatch in copy constructor");
    }

    @Test
    public void testMatches() {
        FlowerSpec spec = new FlowerSpec(FlowerColor.RED, 10, null);
        flower.setColor(FlowerColor.RED);
        flower.setSepalLength(10);

        Assertions.assertTrue(flower.matches(spec), "Flower should match the spec");
    }

    @Test
    public void testDoesNotMatch() {
        FlowerSpec spec = new FlowerSpec(FlowerColor.RED, 15, null);
        flower.setColor(FlowerColor.BLUE);
        flower.setSepalLength(10);

        Assertions.assertFalse(flower.matches(spec), "Flower should not match the spec");
    }

    @Test
    public void testGetColorFromRGB() {
        FlowerColor color = FlowerColor.getColor("#00FF00");
        Assertions.assertEquals(FlowerColor.GREEN, color, "Color mismatch for RGB #00FF00");
    }

    @Test
    public void testGetColorFromRGBInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, 
            () -> FlowerColor.getColor("#123456"), 
            "Expected exception for unknown RGB code");
    }

    @Test
    public void testInvalidRGBFormat() {
        Assertions.assertThrows(IllegalArgumentException.class, 
            () -> FlowerColor.getColor("00FF00"), 
            "Expected exception for missing '#' in RGB code");
    }
}
