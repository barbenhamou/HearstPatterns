import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * name: Bar Ben Hamou.<br>
 * id: 330591207.<br>
 * Discovering the hypernym.
 */
public class DiscoverHypernym {
    /**
     * @param args arguments from the cmd.<br>
     *             The main program.
     */
    public static void main(String[] args) throws IOException {
        Map<String, Integer> hypernyms = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        String path = args[0];
        String lemma = args[1];

        File directory = new File(path);
        File[] files = directory.listFiles();

        NPSuchAsNP p1 = new NPSuchAsNP(padding(lemma));
        SuchNPAsNP p2 = new SuchNPAsNP(padding(lemma));
        NPIncludingNP p3 = new NPIncludingNP(padding(lemma));
        NPEspeciallyNP p4 = new NPEspeciallyNP(padding(lemma));
        NPWhichIsNP p5 = new NPWhichIsNP(padding(lemma));

        assert files != null;
        String line;
        for (File file : files) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                if (!line.contains(padding(lemma))
                        && !line.contains(padding(capitalFirst(lemma)))) {
                    continue;
                }
                p1.analyze(line, hypernyms);
                p2.analyze(line, hypernyms);
                p3.analyze(line, hypernyms);
                p4.analyze(line, hypernyms);
                p5.analyze(line, hypernyms);
            }
        }

        sortAndPrint(hypernyms);
    }

    /**
     * @param s wanted string.<br>
     * @return the string padded with np marks.
     */
    public static String padding(String s) {
        return "<np>" + s + "</np>";
    }

    /**
     * @param s the wanted string.
     * @return the string without the np marks.
     */
    public static String stripping(String s) {
        return s.substring(4, s.length() - 5);
    }

    /**
     * Sorting in increasing by value and print.<br>
     *
     * @param hypernyms the hypernyms' database.
     */
    public static void sortAndPrint(Map<String, Integer> hypernyms) {
        if (hypernyms.isEmpty()) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        }
        Map<String, Integer> sorted = sort(hypernyms);
        for (Map.Entry<String, Integer> element : sorted.entrySet()) {
            System.out.println(stripping(element.getKey()) + ": ("
                    + element.getValue() + ")");
        }
    }

    /**
     * Given a map, return the map sorted.<br>
     *
     * @param <K> Some type.
     * @param <V> some type.
     * @param map the map we'd like to sort.
     * @return the sorted map.
     */
    public static <K, V extends Comparable<V>> Map<K, V> sort(final Map<K, V> map) {
        Comparator<K> valueComparator =
                (k1, k2) -> {
                    int compare = map.get(k2).compareTo(map.get(k1));
                    if (compare == 0) {
                        return 1;
                    } else {
                        return compare;
                    }
                };

        Map<K, V> sortedByValues = new TreeMap<>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    /**
     * @param s the string to do something.<br>
     * @return the string with a capital letter at the beginning.
     */
    public static String capitalFirst(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}