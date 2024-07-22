/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.ed.stream.model;

/**
 *
 * @author dpetr
 */
public class CovidStatistics {

    private String date;
    private String country;
    private int confirmed;
    private int recovered;
    private int deaths;

    public CovidStatistics(String date, String country, int confirmed, int recovered, int deaths) {
        this.date = date;
        this.country = country;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    @Override
    public String toString() {
        return "CovidStatistics{" + "date=" + date + ", country=" + country + ", confirmed=" + confirmed + ", recovered=" + recovered + ", deaths=" + deaths + '}';
    }
}
