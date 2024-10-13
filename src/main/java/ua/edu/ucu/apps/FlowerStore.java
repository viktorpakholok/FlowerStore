package ua.edu.ucu.apps;

import java.util.ArrayList;
import java.util.List;

import ua.edu.ucu.apps.flowers.FlowerBucket;

public class FlowerStore {
    private List<FlowerBucket> flowerBuckets;

    public FlowerStore(){
        flowerBuckets = new ArrayList<>();
    }

    public void add(FlowerBucket flowerBucket) {
        flowerBuckets.add(flowerBucket);
    }

    public FlowerBucket search(){
        for (FlowerBucket flowerBucket : flowerBuckets) {
            // System.out.println(flowerBucket);
            // some realisation
        }
        return new FlowerBucket();
    }
}
