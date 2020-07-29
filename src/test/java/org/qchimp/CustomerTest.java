package org.qchimp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class CustomerTest {
    @Test
    void shouldAddASeriesType() {
        // given
        Customer customer = new Customer(UUID.randomUUID(), "name", LocalDate.now());

        // when
        customer.addSeriesType(SeriesType.DRAMA);

        // then
        assertTrue(customer.getSeriesTypes().contains(SeriesType.DRAMA));
    }

    @Test
    void shouldRemoveASeriesType() {
        // given
        Customer customer = new Customer(UUID.randomUUID(), "name", LocalDate.now());
        customer.addSeriesType(SeriesType.DRAMA);
        assumeTrue(customer.getSeriesTypes().contains(SeriesType.DRAMA));

        // when
        customer.removeSeriesType(SeriesType.DRAMA);

        // then
        assertFalse(customer.getSeriesTypes().contains(SeriesType.DRAMA));

    }


}
