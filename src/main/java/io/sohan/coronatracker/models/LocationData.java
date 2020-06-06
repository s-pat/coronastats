package io.sohan.coronatracker.models;

public class LocationData {
    private String State;
    private int latestsTotal;

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

    @Override
    public String toString() {
        return "LocationData{" +
                "State='" + State + '\'' +
                ", latestsTotal=" + latestsTotal +
                '}';
    }
}
