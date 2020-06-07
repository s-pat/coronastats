package io.sohan.coronatracker.models;

public class LocationData {
    private String State;
    private int latestsTotal;
    private int yesterdayTotal;
    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
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
                "State='" + State + '\'' +
                ", latestsTotal=" + latestsTotal +
                ", yesterdayTotal=" + yesterdayTotal +
                '}';
    }
}
