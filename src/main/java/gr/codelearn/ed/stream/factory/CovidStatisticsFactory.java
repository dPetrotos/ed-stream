/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.ed.stream.factory;

import gr.codelearn.ed.stream.model.CovidStatistics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dpetr
 */
public class CovidStatisticsFactory {

    //2020-01-22,Afghanistan,0,0,0
    public List<CovidStatistics> load(List<String> covidStatisticsStringList) {
        List<CovidStatistics> covidStatisticsList = new ArrayList<>();

        for (String covidStatisticsLine : covidStatisticsStringList) {
            try {
                String line = covidStatisticsLine.replace("\"Korea, South\"", "South Korea");

                String[] elements = line.split(",");

                covidStatisticsList.add(new CovidStatistics(elements[0], elements[1], Integer.parseInt(elements[2]),
                                                            Integer.parseInt(elements[3]),
                                                            Integer.parseInt(elements[4])));

            } catch (Exception e) {
                System.out.println("A record was skipped for being invalid");
            }
        }

        return covidStatisticsList;
    }
}
