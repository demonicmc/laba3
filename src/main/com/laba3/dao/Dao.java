package com.laba3.dao;

import java.util.Collection;

/**
 * Created by set on 23.04.17.
 */
public interface Dao<PK, E> {

    Collection<E> getAll() throws ClassNotFoundException;

    E getById(PK id);

    PK insert(E entity);

    void update(E entity);

    void delete(E entity);

}
