package io.sohan.coronatracker.services;

import io.sohan.coronatracker.models.LocationData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoronaDataService {
    //https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv
    //https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv
    private String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationData> allStats = new ArrayList<>();
    private Set<LocationData> stats = new HashSet<>();
    private int caseCount;
    private int yesterdayCount;
    public List<LocationData> getAllStats() {
        return allStats;
    }

    public int getCaseCount() {
        return caseCount;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")


    public void fetchData() throws IOException, InterruptedException {
         int dailyCasecount = 0;
        List<LocationData> todaysStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DATA_URL)).build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
            //String state = record.get("Province_State");
            //System.out.println(state);
            LocationData locationData = new LocationData();

            locationData.setCovidCountry(record.get("Country/Region"));
            locationData.setRegion(record.get("Province/State"));
            locationData.setLatestsTotal(Integer.parseInt(record.get(record.size() -1)));
            locationData.setYesterdayTotal(Integer.parseInt(record.get(record.size()-2)));
            locationData.setNewCases(Integer.parseInt(record.get(record.size() -1)) -Integer.parseInt(record.get(record.size()-2)));
            dailyCasecount += locationData.getLatestsTotal();
            System.out.println(locationData);
            todaysStats.add(locationData);
            //HashSet<Object> seen=new HashSet<>();
            //todaysStats.removeIf(e->!seen.add(e.getCovidCountry()));
        }

        for(LocationData location : todaysStats){
            System.out.println(location.getCovidCountry());
        }
        this.allStats = todaysStats;
        this.caseCount = dailyCasecount;


    }

}
