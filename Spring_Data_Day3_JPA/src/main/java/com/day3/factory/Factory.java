package com.day3.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {

    public EntityManagerFactory getEntityManagerFactory() {
        String persistenceUnit = "customerPU";

        return Persistence.createEntityManagerFactory(persistenceUnit);
    }

}
