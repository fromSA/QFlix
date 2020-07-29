package org.qchimp;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private UUID id;
    private final String name;
    private final LocalDate date;
    private List<SeriesType> seriesTypes;

    public Customer(UUID id, String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.seriesTypes = new ArrayList<>();
    }

    public void addSeriesType(SeriesType type) {
        this.seriesTypes.add(type);
    }

    public List<SeriesType> getSeriesTypes() {
        return seriesTypes;
    }

    public void removeSeriesType(SeriesType type) {
        seriesTypes.remove(type);
    }

    public UUID getID() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }
}
