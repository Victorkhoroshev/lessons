package net.thumbtack.school.elections.server.dao;

import net.thumbtack.school.elections.server.service.ServerException;

public interface CandidateDao<T> {

    T get(String s) throws ServerException;

    void save(T t);

    boolean contains(String s);

    void delete(T t);
}
