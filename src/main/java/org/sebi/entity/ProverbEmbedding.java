package org.sebi.entity;

import java.util.List;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "byVector", resultClass = ProverbEmbedding.class, query = "SELECT * FROM ProverbEmbedding ORDER BY embedding <-> cast(:vector as vector) LIMIT 2")
public class ProverbEmbedding extends PanacheEntity {

    public String proverb;

    @Column(columnDefinition = "vector(1536)")
    @Type(JsonType.class)
    public List<Double> embedding;
    
    public static List<ProverbEmbedding> findNearestNeighbors(String vector) {
        return  getEntityManager().createNamedQuery("byVector", ProverbEmbedding.class).setParameter("vector",vector).getResultList();   
       }
}
