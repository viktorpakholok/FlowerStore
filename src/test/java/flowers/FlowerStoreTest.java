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
    private FlowerStore flowerStore;

    @BeforeEach
    public void setUp() {
        flowerStore = new FlowerStore();
    }

    @Test
    public void testAddFlowerBucket() {
        FlowerBucket flowerBucket = new FlowerBucket();
        Flower flower = new Flower(50, FlowerColor.RED, 10, FlowerType.ROSE);
        FlowerPack flowerPack = new FlowerPack(flower, 10);

        flowerBucket.add(flowerPack);
        flowerStore.add(flowerBucket);

        List<FlowerBucket> buckets = flowerStore.search(flower);
        Assertions.assertEquals(1, buckets.size(), 
            "Expected 1 bucket after adding it to the store");
    }

    @Test
    public void testSearchMatchingFlowerSpec() {
        FlowerSpec targetSpec = new FlowerSpec(FlowerColor.RED, 10, FlowerType.ROSE);

        Flower flower = new Flower(50, FlowerColor.RED, 10, FlowerType.ROSE);
        FlowerPack matchingPack = new FlowerPack(flower, 10);

        FlowerBucket matchingBucket = new FlowerBucket();
        matchingBucket.add(matchingPack);
        flowerStore.add(matchingBucket);

        List<FlowerBucket> searchResults = flowerStore.search(targetSpec);
        Assertions.assertEquals(1, searchResults.size(), 
            "Expected 1 matching bucket for the given flower spec");
    }

    @Test
    public void testSearchNoMatchingFlowerSpec() {
        FlowerSpec targetSpec = new FlowerSpec(FlowerColor.YELLOW, 5, FlowerType.TULIP);

        Flower flower = new Flower(50, FlowerColor.RED, 10, FlowerType.ROSE);
        FlowerPack nonMatchingPack = new FlowerPack(flower, 10);

        FlowerBucket nonMatchingBucket = new FlowerBucket();
        nonMatchingBucket.add(nonMatchingPack);
        flowerStore.add(nonMatchingBucket);

        List<FlowerBucket> searchResults = flowerStore.search(targetSpec);
        Assertions.assertTrue(searchResults.isEmpty(), 
            "Expected no matching buckets for the given flower spec");
    }

    @Test
    public void testSearchEmptyStore() {
        FlowerSpec targetSpec = new FlowerSpec(FlowerColor.BLUE, 15, FlowerType.CHAMOMILE);

        List<FlowerBucket> searchResults = flowerStore.search(targetSpec);
        Assertions.assertTrue(searchResults.isEmpty(), 
            "Expected no results when searching an empty store");
    }
}