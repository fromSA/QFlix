package org.qchimp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RapportTest {

    @Test
    void shouldReturnInterestWeight() {
        // given
        QFlix registry = new QFlix();

        registry.registerCustomer("customer", LocalDate.now());
        registry.getCustomers().get(0).addSeriesType(SeriesType.DRAMA);

        Set<SeriesType> seriesTypes = new HashSet<>();
        seriesTypes.add(SeriesType.DRAMA);
        registry.registerSeries("name",seriesTypes);

        Customer customer = registry.getCustomers().get(0);
        UUID customerID = customer.getID();

        Series series = registry.getSeries().get(0);
        UUID seriesID = series.getID();

        //when
        long interestWeight = Rapport.getInterestWeight(registry, customerID, seriesID);

        //then
        assertEquals(1, interestWeight);
    }

    @Test
    void shouldReturnTheSeriesWithHighestTotalWeight() {
        // given
        QFlix registry = new QFlix();

        // customers
        registry.registerCustomer("customer1", LocalDate.now());
        registry.getCustomers().get(0).addSeriesType(SeriesType.DRAMA);
        registry.getCustomers().get(0).addSeriesType(SeriesType.TALKSHOW);

        registry.registerCustomer("customer2", LocalDate.now());
        registry.getCustomers().get(1).addSeriesType(SeriesType.DOKUMENTAR);

        registry.registerCustomer("customer3", LocalDate.now());
        registry.getCustomers().get(2).addSeriesType(SeriesType.TALKSHOW);

        registry.registerCustomer("customer4", LocalDate.now());
        registry.getCustomers().get(3).addSeriesType(SeriesType.ANIMASJON);

        // series
        Set<SeriesType> seriesTypes1 = new HashSet<>();
        seriesTypes1.add(SeriesType.TALKSHOW);
        registry.registerSeries("name1",seriesTypes1);
        Series expectedSeries = registry.getSeries().get(0);

        Set<SeriesType> seriesTypes2 = new HashSet<>();
        seriesTypes2.add(SeriesType.DOKUMENTAR);
        registry.registerSeries("name2",seriesTypes2);

        Set<SeriesType> seriesTypes3 = new HashSet<>();
        seriesTypes3.add(SeriesType.DRAMA);
        registry.registerSeries("name3",seriesTypes3);


        // when
        Series seriesWithHighestTotalWeight = Rapport.getSeriesWithHighestTotalWeight(registry);

        // then
        assertEquals(expectedSeries,seriesWithHighestTotalWeight);
    }
}
