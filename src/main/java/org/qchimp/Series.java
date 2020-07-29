package org.qchimp;
import com.google.common.base.Objects;

import java.util.Set;
import java.util.UUID;

public class Series {
    private final String name;
    private final Set<SeriesType> seriesTypes;
    private final UUID id;

    public Series(UUID id, String name, Set<SeriesType> seriesTypes) {
        if(seriesTypes.size()<1) throw new IllegalArgumentException("This series should have at least one seriesType");
        this.id = id;
        this.name = name;
        this.seriesTypes = seriesTypes;
    }

    public void addSeriesType(SeriesType type) {
        seriesTypes.add(type);
    }

    public Set<SeriesType> getSeriesTypes() {
        return seriesTypes;
    }

    public void removeSeriesType(SeriesType type) {
        seriesTypes.remove(type);
    }

    public UUID getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return Objects.equal(id, series.getID());
    }
}
