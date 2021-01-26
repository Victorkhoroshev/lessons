package net.thumbtack.school.elections.server.dao;

import net.thumbtack.school.elections.server.service.ServerException;
import java.util.Set;

public interface VoterDao<T> {
    T get(String s) throws ServerException;

    Set<T> getAll();

    void save(T t) throws ServerException;
}
