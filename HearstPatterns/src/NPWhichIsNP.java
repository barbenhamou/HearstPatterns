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
public class NPWhichIsNP extends ParentPattern implements PatternBehave {
    private String regex = "<np>([^<]*\s?)+<\\/np> (,\s)?which is "
            + "(((an example)|(a kind)|(a class)) of)? ((a|an)\s)?"
            + "(<np>([^<]*\s?)+<\\/np>)";
    private String npEx = "<np>([^<]*\s?)+</np>";
    private String conEx = "(which is).*";
    private Pattern p = Pattern.compile(regex);
    private Pattern np = Pattern.compile(npEx);
    private Pattern concatenation = Pattern.compile(conEx);

    /**
     * Constructor.<br>
     *
     * @param lemma the lemma given.x.
     */
    public NPWhichIsNP(String lemma) {
        super(lemma, "<np>([^<]*\s?)+<\\/np> (,\s)?which is "
                + "(((an example)|(a kind)|(a class)) of)? ((a|an)\s)?"
                + "(<np>([^<]*\s?)+<\\/np>)", "(which is).*");
    }

    @Override
    public void analyze(String line, Map<String, Integer> data) {
        Matcher matchType = p.matcher(line);
        List<String> hearst = new ArrayList<>();
        Matcher con = concatenation.matcher(line);
        boolean appear = false;
        String hypernym = "";

        if (matchType.find()) {
            hearst.add(matchType.group(0));
        } else {
            return;
        }
        while (matchType.find()) {
            hearst.add(matchType.group(0));
        }
        while (con.find()) {
            matchType = p.matcher(con.group(0));
            while (matchType.find()) {
                hearst.add(matchType.group(0));
            }
        }

        for (String match : hearst) {
            Matcher nps = np.matcher(match);

            if (nps.find()) {
                String hyponym = nps.group(0);
                if (getLemma().equalsIgnoreCase(hyponym)) {
                    appear = true;
                }
            } else {
                return;
            }

            while (nps.find()) {
                hypernym = nps.group(0);
                if (appear) {
                    updateMap(hypernym, data);
                }
            }
        }
    }
}