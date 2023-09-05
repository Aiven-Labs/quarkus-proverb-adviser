package org.sebi.model;

import java.util.List;

public class EmbeddingItem {

    public String object;

    public List<Double> embedding;

    public EmbeddingItem(String object, List<Double> embedding) {
        this.object = object;
        this.embedding = embedding;
    }
}
