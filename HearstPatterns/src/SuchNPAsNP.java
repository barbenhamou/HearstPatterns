import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * name: Bar Ben Hamou.<br>
 * id: 330591207.<br>
 * A pattern.
 */
public class SuchNPAsNP extends ParentPattern implements PatternBehave {
    private String regex = "such <np>(([^<]*\s?-?)+)<\\/np> as "
            + "<np>([^<]*\s?)+<\\/np>( (,\s)?<np>([^<]*\s?)+<\\/np>)* ((,\s)?"
            + "(and\s|or\s)<np>([^<]*\s?)+<\\/np>)?";
    private String npEx = "<np>([^<]*\s?)+</np>";
    private Pattern p = Pattern.compile(regex);
    private Pattern np = Pattern.compile(npEx);

    /**
     * Constructor.<br>
     *
     * @param lemma the lemma given.x.
     */
    public SuchNPAsNP(String lemma) {
        super(lemma, "such <np>(([^<]*\s?-?)+)<\\/np> as "
                        + "<np>([^<]*\s?)+<\\/np>( (,\s)?<np>([^<]*\s?)+<\\/np>)* ((,\s)?"
                        + "(and\s|or\s)<np>([^<]*\s?)+<\\/np>)?",
                "");
    }

    @Override
    public void analyze(String line, Map<String, Integer> data) {
        Matcher matchType = p.matcher(line);
        List<String> hearst = new ArrayList<>();

        String hypernym = "";

        while (matchType.find()) {
            hearst.add(matchType.group(0));
        }

        for (String match : hearst) {
            Matcher nps = np.matcher(match);

            if (nps.find()) {
                hypernym = nps.group(0);
            }
            while (nps.find()) {
                String hyponym = nps.group(0);
                if (getLemma().equalsIgnoreCase(hyponym)) {
                    updateMap(hypernym, data);
                }
            }
        }
    }
}