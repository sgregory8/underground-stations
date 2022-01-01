import data.CSVRead;
import entities.station.Station;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

  public static void main(String[] args) throws IOException {
    // get the stations
    List<Station> stations = CSVRead.readStations();

    List<List<Station>> stationCombinations = Combinations.getCombinations(stations, 5);

    int min = 1000;
    List<Station> minimumStations = null;

    for (List<Station> stationList : stationCombinations) {
      int length = stationList.stream().mapToInt(element -> element.getName().length()).sum();
      if (length < min) {
        min = length;
        minimumStations = stationList;
        minimumStations.stream().forEach(station -> System.out.println(station.getName()));
        System.out.println(min);
      }
    }

    minimumStations.stream().forEach(station -> System.out.println(station.getName()));
    System.out.println(min);
  }

  private static List<Station> combinationalMethod(List<Station> inputStations) {
    return null;
  }

  private static List<Station> naiveMethod(List<Station> inputStations) {

    int iterations = 0;

    // define the search characters
    final List<Character> searchChars = new ArrayList<>(Arrays
        .asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    List<Station> matchingStations = new ArrayList<>();

    while (!searchChars.isEmpty()) {

      Station leadingStation = null;
      List<Character> leadingFoundCharacters = null;

      for (Station station : inputStations) {
        iterations++;
        String stationName = station.getName().toLowerCase();
        List<Character> foundChars = new ArrayList<>();
        for (Character character : searchChars) {
          iterations++;
          if (stationName.indexOf(character) != -1) {
            foundChars.add(character);
          }
        }
        if (leadingFoundCharacters == null) {
          leadingFoundCharacters = foundChars;
          leadingStation = station;
        }
        if (foundChars.size() > leadingFoundCharacters.size()) {
          leadingFoundCharacters = foundChars;
          leadingStation = station;
        }
      }
      System.out.println("Round of iterations: " + iterations);
      System.out.println("Characters found: " + leadingFoundCharacters.size());

      leadingFoundCharacters.stream().forEach(character -> searchChars.remove(character));
      matchingStations.add(leadingStation);
      inputStations.remove(leadingStation);
    }
    System.out.println("Iterations of algorithm: " + iterations);
    return matchingStations;
  }

  private static BigInteger factorial(int n) {
    BigInteger result = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
      result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
  }

  private static int combintations(int size, int k) {
    return factorial(size).divide(factorial(k).multiply(factorial(size - k))).intValue();
  }
}
