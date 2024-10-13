package ua.edu.ucu.apps;

import java.util.ArrayList;
import java.util.List;

import ua.edu.ucu.apps.flowers.FlowerBucket;
import ua.edu.ucu.apps.flowers.FlowerSpec;

public class FlowerStore {
    private List<FlowerBucket> flowerBuckets;

    public FlowerStore() {
        flowerBuckets = new ArrayList<>();
    }

    public void add(FlowerBucket flowerBucket) {
        flowerBuckets.add(flowerBucket);
    }

    public List<FlowerBucket> search(FlowerSpec flowerSpec) {
        List<FlowerBucket> resFlowerBuckets = new ArrayList<>();
        for (FlowerBucket flowerBucket : flowerBuckets) {
            if (flowerBucket.search(flowerSpec)) {
                resFlowerBuckets.add(flowerBucket);
            }
        }
        return resFlowerBuckets;
    }
}
