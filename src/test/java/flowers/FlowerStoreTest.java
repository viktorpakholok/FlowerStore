package flowers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import ua.edu.ucu.apps.flowers.Flower;
import ua.edu.ucu.apps.flowers.FlowerBucket;
import ua.edu.ucu.apps.flowers.FlowerPack;
import ua.edu.ucu.apps.flowers.FlowerSpec;
import ua.edu.ucu.apps.flowers.FlowerColor;
import ua.edu.ucu.apps.flowers.FlowerType;
import ua.edu.ucu.apps.FlowerStore;

public class FlowerStoreTest {
    private static final int FLOWER_PRICE = 50;
    private static final int DEFAULT_SEPAL_LENGTH = 10;
    private static final int PACK_QUANTITY = 10;
    private static final int NO_MATCH_SEPAL_LENGTH = 5;
    private static final int EMPTY_SEARCH_SEPAL_LENGTH = 15;

    private FlowerStore flowerStore;

    @BeforeEach
    public void setUp() {
        flowerStore = new FlowerStore();
    }

    @Test
    public void testAddFlowerBucket() {
        FlowerBucket flowerBucket = new FlowerBucket();
        Flower flower = new Flower(
            FLOWER_PRICE, FlowerColor.RED, DEFAULT_SEPAL_LENGTH, FlowerType.ROSE
        );
        FlowerPack flowerPack = new FlowerPack(flower, PACK_QUANTITY);

        flowerBucket.add(flowerPack);
        flowerStore.add(flowerBucket);

        List<FlowerBucket> buckets = flowerStore.search(flower);
        Assertions.assertEquals(
            1, buckets.size(),
            "Expected 1 bucket after adding it to the store"
        );
    }

    @Test
    public void testSearchMatchingFlowerSpec() {
        FlowerSpec targetSpec = new FlowerSpec(
            FlowerColor.RED, DEFAULT_SEPAL_LENGTH, FlowerType.ROSE
        );

        Flower flower = new Flower(
            FLOWER_PRICE, FlowerColor.RED, DEFAULT_SEPAL_LENGTH, FlowerType.ROSE
        );
        FlowerPack matchingPack = new FlowerPack(flower, PACK_QUANTITY);

        FlowerBucket matchingBucket = new FlowerBucket();
        matchingBucket.add(matchingPack);
        flowerStore.add(matchingBucket);

        List<FlowerBucket> searchResults = flowerStore.search(targetSpec);
        Assertions.assertEquals(
            1, searchResults.size(),
            "Expected 1 matching bucket for the given flower spec"
        );
    }

    @Test
    public void testSearchNoMatchingFlowerSpec() {
        FlowerSpec targetSpec = new FlowerSpec(
            FlowerColor.YELLOW, NO_MATCH_SEPAL_LENGTH, FlowerType.TULIP
        );

        Flower flower = new Flower(
            FLOWER_PRICE, FlowerColor.RED, DEFAULT_SEPAL_LENGTH, FlowerType.ROSE
        );
        FlowerPack nonMatchingPack = new FlowerPack(flower, PACK_QUANTITY);

        FlowerBucket nonMatchingBucket = new FlowerBucket();
        nonMatchingBucket.add(nonMatchingPack);
        flowerStore.add(nonMatchingBucket);

        List<FlowerBucket> searchResults = flowerStore.search(targetSpec);
        Assertions.assertTrue(
            searchResults.isEmpty(),
            "Expected no matching buckets for the given flower spec"
        );
    }

    @Test
    public void testSearchEmptyStore() {
        FlowerSpec targetSpec = new FlowerSpec(
            FlowerColor.BLUE, EMPTY_SEARCH_SEPAL_LENGTH, FlowerType.CHAMOMILE
        );

        List<FlowerBucket> searchResults = flowerStore.search(targetSpec);
        Assertions.assertTrue(
            searchResults.isEmpty(),
            "Expected no results when searching an empty store"
        );
    }
}
