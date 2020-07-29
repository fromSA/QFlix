package org.qchimp;

import com.google.common.collect.Ordering;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class QFlixTest {

    @Test
    void shouldRegisterASeries() {
        // given
        QFlix qFlix = new QFlix();
        Set<SeriesType> seriesTypes = new HashSet<>();
        seriesTypes.add(SeriesType.TALKSHOW);

        // when
        qFlix.registerSeries("name", seriesTypes);

        // then
        assertEquals(1, qFlix.getSeries().size());
    }

    @Test
    void shouldRegisterACustomer() {
        // given
        QFlix qFlix = new QFlix();

        // when
        qFlix.registerCustomer("name", LocalDate.now());

        // then
        assertEquals(1, qFlix.getCustomers().size());
    }

    @Test
    void shouldGetCustomerBasedOnID() {
        // given
        QFlix qFlix = new QFlix();
        qFlix.registerCustomer("name", LocalDate.now());
        Customer customer = qFlix.getCustomers().get(0);

        // when
        // then
        assertEquals(customer, qFlix.getCustomer(customer.getID()));
    }

    @Test
    void shouldGetCustomersInteresedInSeries() {
        // given
        QFlix qFlix = new QFlix();
        qFlix.registerCustomer("user1", LocalDate.now());
        qFlix.registerCustomer("user2", LocalDate.now());
        qFlix.registerCustomer("user3", LocalDate.now());

        qFlix.getCustomers().get(0).addSeriesType(SeriesType.DRAMA);
        qFlix.getCustomers().get(1).addSeriesType(SeriesType.DRAMA);

        Set<SeriesType> seriesTypes = new HashSet<>();
        seriesTypes.add(SeriesType.DRAMA);
        qFlix.registerSeries("series1", seriesTypes);
        // when
        UUID seriesID = qFlix.getSeries().get(0).getID();
        List<Customer> customersInterestedInSeries = qFlix.getCustomersInterestedInSeries(seriesID);

        // then
        assertTrue(customersInterestedInSeries.stream().allMatch(customer -> customer.getSeriesTypes().contains(SeriesType.DRAMA)));
    }

    @Test
    void shouldGetAllSeriesWithASeriesType() {
        // given
        QFlix qFlix = new QFlix();

        Set<SeriesType> seriesTypes1 = new HashSet<>();
        seriesTypes1.add(SeriesType.DRAMA);
        qFlix.registerSeries("series1", seriesTypes1);

        Set<SeriesType> seriesTypes2 = new HashSet<>();
        seriesTypes2.add(SeriesType.TALKSHOW);
        qFlix.registerSeries("series2", seriesTypes2);

        Set<SeriesType> seriesTypes3 = new HashSet<>();
        seriesTypes3.add(SeriesType.TALKSHOW);
        seriesTypes3.add(SeriesType.DRAMA);
        qFlix.registerSeries("series3", seriesTypes3);

        Set<SeriesType> seriesTypes4 = new HashSet<>();
        seriesTypes4.add(SeriesType.DRAMA);
        qFlix.registerSeries("series4", seriesTypes4);

        // when
        List<Series> dramaSeries = qFlix.getSeriesWithSeriesType(SeriesType.DRAMA);

        // then
        assertAll(() -> assertEquals(3, dramaSeries.size()),
                () -> assertTrue(dramaSeries.stream()
                        .allMatch(series -> series.getSeriesTypes().contains(SeriesType.DRAMA)))
        );
    }

    @Test
    void shouldGetAllSeriesWithASeriesTypeSortedByTitle() {
        // given
        QFlix qFlix = new QFlix();

        Set<SeriesType> seriesTypes1 = new HashSet<>();
        seriesTypes1.add(SeriesType.DRAMA);
        qFlix.registerSeries("series1", seriesTypes1);

        Set<SeriesType> seriesTypes2 = new HashSet<>();
        seriesTypes2.add(SeriesType.TALKSHOW);
        qFlix.registerSeries("series2", seriesTypes2);

        Set<SeriesType> seriesTypes3 = new HashSet<>();
        seriesTypes3.add(SeriesType.TALKSHOW);
        seriesTypes3.add(SeriesType.DRAMA);
        qFlix.registerSeries("series3", seriesTypes3);

        Set<SeriesType> seriesTypes4 = new HashSet<>();
        seriesTypes4.add(SeriesType.DRAMA);
        qFlix.registerSeries("series4", seriesTypes4);

        // when
        List<Series> dramaSeries = qFlix.getSeriesWithSeriesType(SeriesType.DRAMA);

        // then
        assertAll(() -> assertEquals(3, dramaSeries.size()),
                () -> assertTrue(dramaSeries.stream()
                        .allMatch(series -> series.getSeriesTypes().contains(SeriesType.DRAMA))),
                () -> assertTrue(Ordering.from(Comparator.comparing(Series::getName)).isOrdered(dramaSeries))
        );
    }

    @Test
    void shouldGetCustomersRegisteredBetweenDates() {
        // given
        QFlix qFlix = new QFlix();
        for (int i = 0; i < 10; i++) {
            qFlix.registerCustomer("user" + i, LocalDate.of(2020,i+1,1));
        }

        // when

        LocalDate from = LocalDate.of(2020,1,1);
        LocalDate to = LocalDate.of(2020,5,1);
        List<Customer> customersRegisteredBetweenDates = qFlix.getCustomersRegisteredBetween(from, to);

        // then
        assertEquals(3,customersRegisteredBetweenDates.size());
    }
}
