package org.qchimp;

import java.util.NoSuchElementException;
import java.util.UUID;

public class Rapport {
    public static long getInterestWeight(QFlix registry, UUID customerID, UUID seriesID) {
        Series series = registry.getSeries(seriesID);
        Customer customer = registry.getCustomer(customerID);
        return customer.getSeriesTypes().stream().filter(type -> series.getSeriesTypes().contains(type)).count();
    }

    public static Series getSeriesWithHighestTotalWeight(QFlix registry) {
        Series maxSeries = null;
        long maxWeight = 0;
        for (Series series : registry.getSeries()) {
            for (Customer customer : registry.getCustomers()) {
                long interestWeight = getInterestWeight(registry, customer.getID(), series.getID());
                if(maxWeight < interestWeight) {
                    maxWeight = interestWeight;
                    maxSeries = series;
                }
            }
        }
        if(maxSeries == null) throw new NoSuchElementException("There are no series in the registry");
        return maxSeries;


    }
}
