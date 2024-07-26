/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.ed.stream.service;

import gr.codelearn.ed.stream.model.CovidStatistics;
import gr.codelearn.ed.stream.model.DeathsPerCountry;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author dpetr
 */
public class ReportingService {

    public CovidStatistics calculateMostConfirmedCases(final List<CovidStatistics> covidStatisticsList) {
        return covidStatisticsList
                .stream()
                .max(Comparator.comparing(CovidStatistics::getConfirmed))
                .orElseThrow(() -> new RuntimeException("No Covid Statistics found"));
    }

    public long calcuateNumberOfRecordsForGreece(final List<CovidStatistics> covidStatisticsList) {
        return covidStatisticsList
                .stream()
                .filter(covidStatistics -> covidStatistics.getCountry().equals("Greece"))
                .count();
    }

    public Map<String, Optional<CovidStatistics>> calculateMaxDeathsPerCountry(final List<CovidStatistics> covidStatisticsList) {
        return covidStatisticsList
                .stream()
                .collect(Collectors.groupingBy(CovidStatistics::getCountry, Collectors.maxBy(Comparator.comparing(CovidStatistics::getDeaths))));
    }

    public List<DeathsPerCountry> calculateMaxDeathsPerCountrySorted(final List<CovidStatistics> covidStatisticsList) {
        return calculateMaxDeathsPerCountry(covidStatisticsList)
                .entrySet()
                .stream()
                .map(mapEntry -> new DeathsPerCountry(mapEntry.getKey(),
                                                        mapEntry.getValue()
                                                                .orElseThrow(() -> new RuntimeException("No statistics found"))
                                                                .getDeaths()))
                .sorted(Comparator.comparing(DeathsPerCountry::deaths, Comparator.reverseOrder()).thenComparing(DeathsPerCountry::country))
                .toList();
    }

    public Double calculateAverageNumberOfDeathsGlobally(final List<CovidStatistics> covidStatisticsList) {
        return calculateMaxDeathsPerCountrySorted(covidStatisticsList)
                .stream()
                .collect(Collectors.averagingDouble(DeathsPerCountry::deaths));

//        return calculateMaxDeathsPerCountry(covidStatisticsList)
//                .stream()
//                .mapToDouble(DeathsPerCountry::deaths)
//                .average()
//                .orElseThrow();
    }

    public List<String> findCountriesWithMoreThanAverageDeaths(final List<CovidStatistics> covidStatisticsList) {
        return calculateMaxDeathsPerCountrySorted(covidStatisticsList)
                .stream()
                .filter(deathsPerCountry -> deathsPerCountry.deaths() > calculateAverageNumberOfDeathsGlobally(covidStatisticsList))
                .map(DeathsPerCountry::country)
                .sorted()
                .toList();
    }

    public Integer calculateTotalDeathsByDateGlobally(final List<CovidStatistics> covidStatisticsList, final LocalDate date) {
        return covidStatisticsList
                .stream()
                .filter(covidStatistics ->
                        LocalDate
                                .parse(covidStatistics.getDate())
                                .equals(LocalDate.of(date.getYear(), date.getMonth(), 1)))
                .collect(Collectors.summingInt(CovidStatistics::getDeaths));

    }
}
