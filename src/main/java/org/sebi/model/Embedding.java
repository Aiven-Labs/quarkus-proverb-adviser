package org.sebi.model;

import java.util.List;

public class Embedding {

    public String object;

    public List<EmbeddingItem> data;

    public Embedding(String object, List<EmbeddingItem> data) {
        this.object = object;
        this.data = data;
    }
    
    
}
