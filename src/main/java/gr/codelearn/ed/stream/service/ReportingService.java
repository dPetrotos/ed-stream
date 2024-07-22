/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.ed.stream.service;

import gr.codelearn.ed.stream.model.CovidStatistics;
import gr.codelearn.ed.stream.model.DeathsPerCountry;
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
}
