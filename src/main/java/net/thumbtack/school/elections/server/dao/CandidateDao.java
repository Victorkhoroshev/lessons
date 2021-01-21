package net.thumbtack.school.elections.server.dao;

import net.thumbtack.school.elections.server.model.Candidate;
import net.thumbtack.school.elections.server.service.ServerException;

import java.util.Set;

public interface CandidateDao<T> {

    T get(String s) throws ServerException;

    void save(T t) throws ServerException;

    boolean contains(String s);

    void delete(T t);
}
