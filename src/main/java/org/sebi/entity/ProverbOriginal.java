package org.sebi.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ProverbOriginal extends PanacheEntity {

    public String proverb;
    
}
