/**
 * name: Bar Ben Hamou.<br>
 * id: 330591207.<br>
 * A pattern.
 */
public class NPSuchAsNP extends ParentPattern implements PatternBehave {
    /**
     * Constructor.<br>
     *
     * @param lemma the lemma given.
     */
    public NPSuchAsNP(String lemma) {
        super(lemma, "<np>[^<]+</np> (,\s)?such as <np>[^<]+</np>( ?, <np>"
                + "([^<])+</np>)*(\s(,\s)?((and\s)|(or\s))<np>([^<])+</np>)?",
                "(such as).*");
    }
}