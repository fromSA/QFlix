package org.qchimp;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class SeriesTest {

    @Test
    void shouldAddASeriesType() {
        // given
        Set<SeriesType> seriesTypes = new HashSet<>();
        seriesTypes.add(SeriesType.TALKSHOW);

        Series series = new Series(UUID.randomUUID(), "name", seriesTypes);

        // when
        series.addSeriesType(SeriesType.DRAMA);

        // then
        assertTrue(series.getSeriesTypes().contains(SeriesType.DRAMA));
    }

    @Test
    void shouldRemoveSeriesType() {
        // given
        Set<SeriesType> seriesTypes = new HashSet<>();
        seriesTypes.add(SeriesType.TALKSHOW);
        Series series = new Series(UUID.randomUUID(), "name", seriesTypes);
        series.addSeriesType(SeriesType.DRAMA);
        assumeTrue(series.getSeriesTypes().contains(SeriesType.DRAMA));
        // when
        series.removeSeriesType(SeriesType.DRAMA);

        // then
        assertFalse(series.getSeriesTypes().contains(SeriesType.DRAMA));
    }

    @Test
    void shouldContainAtLeastOneSeriesType() {
        // given
        Set<SeriesType> seriesTypes = new HashSet<>();
        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new Series(UUID.randomUUID(), "name", seriesTypes));
    }
}
