/**
 * name: Bar Ben Hamou.<br>
 * id: 330591207.<br>
 * A pattern.
 */
public class NPEspeciallyNP extends ParentPattern implements PatternBehave {
    /**
     * Constructor.<br>
     *
     * @param lemma the lemma given.x.
     */
    public NPEspeciallyNP(String lemma) {
        super(lemma, "<np>[^<]+</np> (,\s)?especially <np>[^<]+</np>(\s?, <np>"
                        + "([^<])+</np>)*( (,\s)?((and\s)|(or\s))<np>" +
                        "([^<]*\s?)+</np>)?",
                "(especially).*");
    }
}