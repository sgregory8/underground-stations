import entities.station.Station;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {

  static Station belsizePark = new Station("Belsize Park", null);

  static <T> List<List<Station>> getCombinations(List<T> input, int combinationSize) {
    int k = combinationSize;                             // sequence length

    final List<Character> searchChars = new ArrayList<>(Arrays
        .asList('z', 'j', 'q', 'x', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n',
            'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'y'));

    List<List<Station>> subsets = new ArrayList<>();

    int[] s = new int[k];                  // here we'll keep indices
    // pointing to elements in input array

    if (k <= input.size()) {
      // first index sequence: 0, 1, 2, ...
      for (int i = 0; (s[i] = i) < k - 1; i++) {
        ;
      }
      checkSubset((List<Station>) getSubset(input, s), searchChars, subsets);
      for (; ; ) {
        int i;
        // find position of item that can be incremented
        for (i = k - 1; i >= 0 && s[i] == input.size() - k + i; i--) {
          ;
        }
        if (i < 0) {
          break;
        }
        s[i]++;                    // increment this item
        for (++i; i < k; i++) {    // fill up remaining items
          s[i] = s[i - 1] + 1;
        }
        checkSubset((List<Station>) getSubset(input, s), searchChars, subsets);
      }
    }
    return subsets;
  }

  // generate actual subset by index sequence
  private static <T> List<T> getSubset(List<T> input, int[] subset) {
    List<T> result = new ArrayList(subset.length);
    for (int i = 0; i < subset.length; i++) {
      result.add(input.get(subset[i]));
    }
    return result;
  }

  private static void checkSubset(List<Station> subset, List<Character> searchChars,
      List<List<Station>> subsets) {
    subset.add(belsizePark);
    String concatStations = subset.stream().map(Station::getName)
        .reduce("", (a, b) -> a + b).toLowerCase();
    for (Character character : searchChars) {
      if (concatStations.indexOf(character) == -1) {
        return;
      }
    }
    subsets.add(subset);
  }
}
