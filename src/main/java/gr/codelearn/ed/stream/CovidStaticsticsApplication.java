/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.ed.stream;

import gr.codelearn.ed.stream.factory.CovidStatisticsFactory;
import gr.codelearn.ed.stream.model.CovidStatistics;
import gr.codelearn.ed.stream.service.ReportingService;
import gr.codelearn.ed.stream.util.HTMLReader;
import gr.codelearn.ed.stream.util.Reader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author dpetr
 */
public class CovidStaticsticsApplication {

    private static final String URI_STRING = "https://raw.githubusercontent.com/datasets/covid-19/master/data/countries-aggregated.csv";

    public static void main(String[] args) {
        try {
            System.out.println("Loading statistics from the web...");
            Reader reader = new HTMLReader();
            List<String> covidStatisticsStringList = reader.read(URI_STRING);

            System.out.println("Converting to Java objects...");
            CovidStatisticsFactory covidStatisticsFactory = new CovidStatisticsFactory();
            List<CovidStatistics> covidStatisticsList = covidStatisticsFactory.load(covidStatisticsStringList);

//            covidStatisticsList.forEach(System.out::println);
            System.out.println("------------------------------REPORTING START HERE------------------------------");

            ReportingService reportingService = new ReportingService();
            CovidStatistics mostConfirmedCases = reportingService.calculateMostConfirmedCases(covidStatisticsList);

            System.out.println(String.format("Most confirmed cases in the world: Country %s, Confirmed cases: %d, Date: %s",
                                             mostConfirmedCases.getCountry(),
                                             mostConfirmedCases.getConfirmed(),
                                             mostConfirmedCases.getDate()));

            long numberOfCasesForGreece = reportingService.calcuateNumberOfRecordsForGreece(covidStatisticsList);
            System.out.println("Number of records for Greece: " + numberOfCasesForGreece);

            System.out.println("Max deaths per country");
//            Map<String, Optional<CovidStatistics>> maxDeathsPerCountry = reportingService.calculateMaxDeathsPerCountry(covidStatisticsList);
//            maxDeathsPerCountry.forEach((country, maxDeathsStatistics) -> System.out.println(String.format("Country: %s, Deaths: %d", country,
//                                                                                                             maxDeathsStatistics.orElseThrow()
//                                                                                                                     .getDeaths())));

            var maxDeathsPerCountry = reportingService.calculateMaxDeathsPerCountrySorted(covidStatisticsList);
            maxDeathsPerCountry.forEach(deathsPerCountry -> System.out.println(String.format("Country %s:, Deaths: %d", deathsPerCountry.country(),
                                                                                               deathsPerCountry.deaths())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
