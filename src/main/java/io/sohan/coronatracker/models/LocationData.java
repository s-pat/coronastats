package io.sohan.coronatracker.models;

import java.util.Objects;

public class LocationData {
    private String covidCountry;
    private int latestsTotal;
    private int yesterdayTotal;
    private int newCases;
    public String getCovidCountry() {
        return covidCountry;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    @Override
    public int hashCode() {
        return Objects.hash(covidCountry, latestsTotal, yesterdayTotal);
    }

    public void setCovidCountry(String covidCountry) {
        this.covidCountry = covidCountry;
    }

    public int getLatestsTotal() {
        return latestsTotal;
    }

    public void setLatestsTotal(int latestTotal) {
        this.latestsTotal = latestTotal;
    }

    public int getYesterdayTotal() {
        return yesterdayTotal;
    }

    public void setYesterdayTotal(int yesterdayTotal) {
        this.yesterdayTotal = yesterdayTotal;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "State='" + covidCountry + '\'' +
                ", latestsTotal=" + latestsTotal +
                ", yesterdayTotal=" + yesterdayTotal +
                '}';
    }
}
