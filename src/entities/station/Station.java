package entities.station;

public class Station {

  private final String name;
  private final String[] lines;

  public String getName() {
    return name;
  }

  public String[] getLines() {
    return lines;
  }

  public Station(String name, String[] lines) {
    this.name = name;
    this.lines = lines;
  }
}
