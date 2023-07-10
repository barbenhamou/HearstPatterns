/**
 * name: Bar Ben Hamou.<br>
 * id: 330591207.<br>
 * A pattern.
 */
public class NPIncludingNP extends ParentPattern implements PatternBehave {
    /**
     * Constructor.<br>
     *
     * @param lemma the lemma given.x.
     */
    public NPIncludingNP(String lemma) {
        super(lemma, "<np>[^<]+</np> (,\s)?including <np>[^<]+</np>(\s?, <np>"
                        + "([^<])+</np>)*( (,\s)?((and\s)|(or\s))<np>([^<])+</np>)?",
                "(including).*");
    }
}