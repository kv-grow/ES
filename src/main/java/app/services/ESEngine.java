package app.services;

import java.io.IOException;

import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;

public class ESEngine {
    private final JestClient jestClient;
    private final ESParameters esParameters;

    public ESEngine(final JestClient jestClient, final ESParameters esParameters) {
        this.jestClient = jestClient;
        this.esParameters = esParameters;
    }

    public DocumentResult addObject(Object o) {
        Index index = new Index.Builder(o)
                .index(esParameters.getIndex())
                .type(esParameters.getType())
                .build();
        try {
            final DocumentResult documentResult = jestClient.execute(index);
            return documentResult;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
