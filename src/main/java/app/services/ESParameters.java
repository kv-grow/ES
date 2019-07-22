package app.services;

public class ESParameters {
    private final int resultsSize;
    private final String type;
    private final String index;

    public ESParameters(final String index, final String type, final int resultsSize) {
        this.resultsSize = resultsSize;
        this.type = type;
        this.index = index;
    }

    public int getResultsSize() {
        return resultsSize;
    }

    public String getType() {
        return type;
    }

    public String getIndex() {
        return index;
    }
}
