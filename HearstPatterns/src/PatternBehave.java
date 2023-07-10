import java.util.Map;

/**
 * name: Bar Ben Hamou.<br>
 * id: 330591207.<br>
 * A model of a hearst pattern.
 */
public interface PatternBehave {
    /**
     * Analyzing the line given from the main program according to the
     * appropriate regex.<br>
     *
     * @param line the line given from the main to be analyzed.
     * @param data the database with all the hypernyms and the number of
     *             relation.
     * */
    void analyze(String line, Map<String, Integer> data);

    /**
     * Updating the database based of the given hypernym and database's map.<br>
     * Called from the analyze function.<br>
     *
     * @param data the database's map.
     * @param hypernym teh given hypernym.
     * */
    void updateMap(String hypernym, Map<String, Integer> data);
}