package data;

import entities.station.Station;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVRead {


  private static List<Station> readFromInputStream(InputStream inputStream)
      throws IOException {

    ArrayList<Station> stations = new ArrayList<>();

    try (BufferedReader br
        = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      int lineCount = 0;
      while ((line = br.readLine()) != null) {
        if (lineCount != 0) {
          try {
            String connectingStations = line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"'));
            String[] connectingStationPieces = connectingStations.replace(", ", ",").split(",");
            line = line.replaceAll(connectingStations, "");
            String[] tokens = line.split(",");
            stations
                .add(new Station(tokens[2], connectingStationPieces));
          } catch (IndexOutOfBoundsException ex) {
            String[] tokens = line.split(",");
            stations
                .add(new Station(tokens[2], new String[]{tokens[5]}));
          }
        }
        lineCount++;
      }
    }
    return stations;
  }

  public static List<Station> readStations() throws IOException {
    ClassLoader classLoader = CSVRead.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("data/data.csv");
    return readFromInputStream(inputStream);
  }

}
