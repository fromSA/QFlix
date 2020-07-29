package org.qchimp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class QFlix {
    private List<Series> series;
    private List<Customer> customers;

    public QFlix() {
        series = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void registerSeries(String name, Set<SeriesType> seriesTypes) {
        series.add(new Series(UUID.randomUUID(), name, seriesTypes));
    }

    public List<Series> getSeries() {
        return series;
    }

    public void registerCustomer(String name, LocalDate date) {
        customers.add(new Customer(UUID.randomUUID(), name, date));
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(UUID id) {
        List<Customer> collection = customers.stream().filter(customer -> customer.getID().equals(id)).collect(Collectors.toList());
        if (collection.size() > 1)
            throw new IllegalStateException("There are more than one customers with the same id");
        else return collection.get(0);
    }

    public Series getSeries(UUID id) {
        List<Series> collection = series.stream().filter(series1 -> series1.getID().equals(id)).collect(Collectors.toList());
        if (collection.size() > 1)
            throw new IllegalStateException("There are more than one series with the same id");
        else if (collection.isEmpty()) throw new NoSuchElementException("There are no series with this id");
        else return collection.get(0);
    }

    public List<Customer> getCustomersInterestedInSeries(UUID seriesID) {
        Set<SeriesType> seriesTypes = getSeries(seriesID).getSeriesTypes();
        return customers.stream()
                .filter(customer -> seriesTypes.stream()
                        .anyMatch(type -> customer.getSeriesTypes().contains(type)))
                .collect(Collectors.toList());
    }

    public List<Series> getSeriesWithSeriesType(SeriesType type) {
        return series.stream()
                .filter(series1 -> series1.getSeriesTypes().contains(type))
                .sorted(Comparator.comparing(Series::getName))
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomersRegisteredBetween(LocalDate from, LocalDate to) {
        return customers.stream().filter(customer -> customer.getDate().isAfter(from)
                && customer.getDate().isBefore(to)).collect(Collectors.toList());
    }
}
