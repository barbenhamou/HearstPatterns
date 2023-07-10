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
public abstract class ParentPattern implements PatternBehave {
    private String lemma;
    private String regex;
    private String npEx = "<np>([^<]*\s?)+</np>";
    private String conEx;
    private Pattern p;
    private Pattern np;
    private Pattern concatenation;

    /**
     * Constructor.<br>
     *
     * @param lemma the lemma given.
     * @param regex the appropriate regex.
     * @param conEx the concatenation of the given regex.
     */
    public ParentPattern(String lemma, String regex, String conEx) {
        this.lemma = lemma;
        this.regex = regex;
        this.conEx = conEx;
        p = Pattern.compile(this.regex);
        np = Pattern.compile(npEx);
        concatenation = Pattern.compile(this.conEx);

    }

    @Override
    public void analyze(String line, Map<String, Integer> data) {
        Matcher matchType = p.matcher(line);
        List<String> hearst = new ArrayList<>();
        Matcher con = concatenation.matcher(line);
        String hypernym = "", group = "";

        while (matchType.find()) {
            hearst.add(matchType.group(0));
        }
        while (con.find()) {
            group = con.group(0);
            matchType = p.matcher(group);
            while (matchType.find()) {
                group = matchType.group(0);
                if (hearst.contains(group)) {
                    continue;
                }
                hearst.add(group);
            }
        }

        for (String match : hearst) {
            Matcher nps = np.matcher(match);

            if (nps.find()) {
                hypernym = nps.group(0);
            }

            while (nps.find()) {
                String hyponym = nps.group(0);
                if (this.lemma.equalsIgnoreCase(hyponym)) {
                    updateMap(hypernym, data);
                }
            }
        }
    }

    @Override
    public void updateMap(String hypernym, Map<String, Integer> data) {
        if (data.containsKey(hypernym)) {
            data.replace(hypernym, data.get(hypernym) + 1);
        } else {
            data.put(hypernym, 1);
        }
    }

    /**
     * @return the given lemma.
     */
    public String getLemma() {
        return this.lemma;
    }
}