package io.sohan.coronatracker.services;

import io.sohan.coronatracker.models.LocationData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaDataService {

    private String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private List<LocationData> allStats = new ArrayList<>();
    private int caseCount;

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
            locationData.setState(record.get("Country/Region"));
            locationData.setLatestsTotal(Integer.parseInt(record.get(record.size() - 1)));
            dailyCasecount += locationData.getLatestsTotal();
            System.out.println(locationData);
            todaysStats.add(locationData);
        }

        this.allStats = todaysStats;
        this.caseCount = dailyCasecount;


    }

}
