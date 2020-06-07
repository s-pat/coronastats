package io.sohan.coronatracker.models;

import java.util.Objects;

public class LocationData {
    private String covidCountry;
    private int latestsTotal;
    private int yesterdayTotal;
    public String getCovidCountry() {
        return covidCountry;
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
